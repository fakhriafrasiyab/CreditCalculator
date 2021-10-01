package com.example.creditcalculator;

public class CreditCalculatorMain {
    private static int calculateMonthlyPayment(int term, int amount) {
        int interestRate;
        if (term >= 3 && term <= 12) {
            interestRate = 18;
        } else if (term >= 13 && term <= 24) {
            interestRate = 20;
        } else {
            interestRate = 22;
        }
        double percent = ((double) interestRate) / 100;
        double per = percent / 12;
        double pow = Math.pow(1 + per, term);
        return (int) (amount * ((per * pow) / (pow - 1)));
    }

    static int binarySearch(int l, int r, int requestedMonthlyAmount, int totalAmount) {
        int prev = -1;
        while (r > l) {
            int mid = l + (r - l) / 2;

            int monthlyPayment = calculateMonthlyPayment(mid, totalAmount);

            if (monthlyPayment > requestedMonthlyAmount)
                l = mid + 1;
            else {
                prev = mid;
                r = mid;
            }
        }
        return prev;
    }

    public static void main(String args[]) {

        int totalAmount = 2000;
        int requestedTerm = 12;
        int requestedMonthlyAmount = 200;

        // L = minimum ay sayidir => 1000/100 = 10
        int L = totalAmount / requestedMonthlyAmount;
        int R = 48;

        int bestTerm = binarySearch(L, R, requestedMonthlyAmount, totalAmount);
        int possibleMonthlyAmount = calculateMonthlyPayment(bestTerm, totalAmount);
        if (bestTerm > requestedTerm)
            System.out.println("sorry not possible  " + bestTerm + "  " + requestedTerm + "   " + possibleMonthlyAmount);
        else
            System.out.println("Best Term is " + bestTerm + "months");
    }
}



