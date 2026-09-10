package com.thomasbuilds.incidentflow;

public class Main {
    public static double calculateAverageLatency(int[] latencies){
        int total = 0;
        for(int latency : latencies){
            total += latency;
        }

        double division = (double) total / latencies.length;

        return division;
    }

    public static void main(String[] args) {
        int[] latencies = {100, 200, 301};

        double result = calculateAverageLatency(latencies);

        System.out.println(result);
    }
}