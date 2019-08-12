package com.schneide.abas.ccd.orange.itest.two;

import static spark.Spark.*;

import java.util.Random;

public class RandomNumberMicroservice {

    public static void main(String[] args) {
        get("/rng/:threshold", (req, res) -> {
        	final int threshold = Integer.parseInt(req.params("threshold"));
        	final int result = new Random().nextInt(threshold);
        	System.out.println(threshold + " --> " + result);
        	return String.valueOf(result);
        });
    }
}