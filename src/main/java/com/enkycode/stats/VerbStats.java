package com.enkycode.stats;

import java.util.ArrayList;
import java.util.List;

public class VerbStats {
    private double presentScore;
    private double presentAccuracy;
    private double preteriteScore;
    private double preteriteAccuracy;
    private double imperfectScore;
    private double imperfectAccuracy;
    private double futureScore;
    private double futureAccuracy;
    private double conditionalScore;
    private double conditionalAccuracy;

    public void updatePresent(double score, double accuracy) {
        presentScore = score;
        presentAccuracy = accuracy;
    }
    public void updatePreterite(double score, double accuracy) {
        preteriteScore = score;
        preteriteAccuracy = accuracy;
    }
    public void updateImperfect(double score, double accuracy) {
        imperfectScore = score;
        imperfectAccuracy = accuracy;
    }
    public void updateFuture(double score, double accuracy) {
        futureScore = score;
        futureAccuracy = accuracy;
    }
    public void updateConditional(double score, double accuracy) {
        conditionalScore = score;
        conditionalAccuracy = accuracy;
    }

    public void printStats() {
        double roundedAccuracy = Math.round(presentAccuracy * 1000) / 10.0;
        System.out.println("-------------Stats--------------");
        System.out.println("---Tense-----Score---Accuracy---");
        System.out.println("  Present     "+presentScore+"     "+roundedAccuracy+"%");
        roundedAccuracy = Math.round(preteriteAccuracy * 1000) / 10.0;
        System.out.println("  Preterite   "+preteriteScore+"     "+roundedAccuracy+"%");
        roundedAccuracy = Math.round(imperfectAccuracy * 1000) / 10.0;
        System.out.println("  Imperfect   "+imperfectScore+"     "+roundedAccuracy+"%");
        roundedAccuracy = Math.round(futureAccuracy * 1000) / 10.0;
        System.out.println("  Future      "+futureScore+"     "+roundedAccuracy+"%");
        roundedAccuracy = Math.round(conditionalAccuracy * 1000) / 10.0;
        System.out.println("  Conditional "+conditionalScore+"     "+roundedAccuracy+"%");
    }

    public void printSuggestion() {
        List<String> suggestions = new ArrayList<>();
        List<String> tenseList = new ArrayList<>(List.of("Present Tense", "Preterite Tense", "Imperfect Tense", "Future Tense", "Conditional Tense"));
        List<Double> accuracyList = new ArrayList<>(List.of(presentAccuracy, preteriteAccuracy, imperfectAccuracy, futureAccuracy, conditionalAccuracy));
        int lowestIndex;
        for (int i = 0; i < 3; i++) {
            lowestIndex = findMinimum(accuracyList);
            accuracyList.remove(lowestIndex);
            suggestions.add(tenseList.remove(lowestIndex));
        }
        System.out.println("---Practice Suggestions---");
        for (String suggestion : suggestions) {System.out.println(suggestion);}
    }
    public int findMinimum(List<Double> list) {
        assert !list.isEmpty();
        double min = list.getFirst();
        int idx = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) < min) {
                min = list.get(i);
                idx = i;
            }
        }
        return idx;
    }
}
