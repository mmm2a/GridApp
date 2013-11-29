package com.morgan.grid.server.common.feature;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.io.Closeables;
import com.google.common.io.InputSupplier;
import com.google.common.io.Resources;
import com.google.inject.Inject;
import com.morgan.grid.shared.common.feature.Feature;

/**
 * A utility class to help with reading and parsing the feature file.
 *
 * @author mark@mark-morgan.net (Mark Morgan)
 */
class FeatureFile {

  private static final String FILE_RESOURCE_PATH = "META-INF/features.in";
  private static final Pattern LINE_PATTERN = Pattern.compile(
      "^\\s*(\\w+)\\s*\\(([^\\)]+)\\)\\s*$");
  private static final Splitter PREDICATE_SPLITTER = Splitter.on('|').trimResults();

  private final ImmutableSet<FeaturePredicateParser> featurePredicateParsers;

  @Inject FeatureFile(Set<FeaturePredicateParser> featurePredicateParsers) {
    this.featurePredicateParsers = ImmutableSet.copyOf(featurePredicateParsers);
  }

  @VisibleForTesting InputSupplier<? extends Reader> getFileReader() {
    return Resources.newReaderSupplier(Resources.getResource(FILE_RESOURCE_PATH), Charsets.UTF_8);
  }

  private Predicate<FeatureContext> parsePredicates(String predicatesLine) {
    ImmutableList.Builder<Predicate<FeatureContext>> predicatesBuilder = ImmutableList.builder();
    for (String predicateString : PREDICATE_SPLITTER.split(predicatesLine)) {
      Predicate<FeatureContext> predicate = null;
      for (FeaturePredicateParser parser : featurePredicateParsers) {
        predicate = parser.parsePredicateString(predicateString);
        if (predicate != null) {
          break;
        }
      }

      Preconditions.checkState(predicate != null,
          "Unable to parse feature predicate %s", predicateString);
      predicatesBuilder.add(predicate);
    }
    ImmutableList<Predicate<FeatureContext>> predicates = predicatesBuilder.build();
    switch (predicates.size()) {
      case 0:
        return Predicates.alwaysTrue();

      case 1:
        return Iterables.getOnlyElement(predicates);

      default:
        return Predicates.or(predicates);
    }
  }

  ImmutableMap<Feature, Predicate<FeatureContext>> readFeaturesFile() throws IOException {
    ImmutableMap.Builder<Feature, Predicate<FeatureContext>> mapBuilder = ImmutableMap.builder();
    Reader reader = getFileReader().getInput();
    try {
      LineNumberReader bufferedReader = new LineNumberReader(reader);
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        line = line.trim();
        if (line.isEmpty()) {
          continue;
        }

        Matcher matcher = LINE_PATTERN.matcher(line);
        Preconditions.checkState(matcher.matches(),
            "Unable to parse line %s of features file", bufferedReader.getLineNumber());
        String featureString = matcher.group(1);
        String predicates = matcher.group(2);
        Feature feature = Feature.valueOf(featureString);
        mapBuilder.put(feature, parsePredicates(predicates));
      }
      return mapBuilder.build();
    } finally {
      Closeables.close(reader, true);
    }
  }
}
