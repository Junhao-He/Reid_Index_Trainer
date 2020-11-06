package com.znv.datascience.coarsetrain;

import java.lang.reflect.Array;

import static com.esotericsoftware.kryo.util.Util.string;

public class TestFunc {
    private void printTime(long time){
        System.out.println( new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(time)));
    }

    private static final int TYPE_HUMAN = 1;
    private static final int TYPE_BIKE = 4;
    private static final int TYPE_CAR = 2;

    public double compare(int type, String feature1, String feature2)
    {
        return compare(type, java.util.Base64.getDecoder().decode(feature1.getBytes()), java.util.Base64.getDecoder().decode(feature2.getBytes()));
    }

    public double compare(int type, byte[] feature1, byte[] feature2)
    {
        if (null == feature1 || null == feature2)
            return 0.0;

        int featureLength = 1536;
        int feature1Start = 0;
        int feature2Start = 0;
        if (TYPE_HUMAN == type || TYPE_BIKE == type)
        {
            featureLength = 1536;
            if (feature1.length == 1584) feature1Start = 48;
            if (feature2.length == 1584) feature2Start = 48;
        }
        else if (TYPE_CAR == type)
        {
            featureLength = 512;
            if (feature1.length == 560) feature1Start = 48;
            if (feature2.length == 560) feature2Start = 48;
        }

        double score = 0.0d;
        for (int i = 0; i < featureLength; i++)
        {
            score += feature1[i + feature1Start] * feature2[i + feature2Start];
        }
        score /= 16129;
        score = (score + 1) / 2.0d;
        return score > 1?1:(score < 0?0:score);
    }

    public static void main(String[] args) {
        TestFunc tt = new TestFunc();
//        tt.printTime(1604364860564L);
        String feature1 = "SkxUQgkAAABsAAAAAQAAAIkrml8AAAAAAAAAAAAAAAAPJj9ydQEAAAAGAAAAAAAAAAD+/wD/CAb//wH//v7+/v39/f39//3+/fv9/wAA/vz/AAQEAAUAAP/+AAAEAwD/AQAAAP7++vj8/vz9//7/AAD/BAQAAAAA//z9/v3+AAIJBwMA/v38/f4A/v39/v39/gABAAAA/f8CAQAAAP79AP39+vr7/fn6//////7+AP79//4AAAACAgQCAP/9/v4A/ff4/f7/AP39/v7+CAYDAQAA/v39//3+//37/f7//Pr6/f8AAQkMCAkE//7+//8A/fn8/v3//vz9//8AAP0AAAMG/v3+//7//vz+AP/+AQIDAgAAAf8AAQEA/v/+/v3+///+/wAB/fz8/v3+AQEAAAAAAQIBAP8A/fr8////AAIAAAID////AP///Pr8/vv8/fr8/v39/P0AAP///wAAAP//AAUGAwQCAAD///8A+vj4+/v+/QABAAIC//z9//7/AP4AAP39AQT///7+AwD/AP7+/P8CAP3+AQH+//79+/v9/vv9BAUCAP/////+//8A/wEAAP4A/gEAAAIC/Pn7/fz9/wAAAAUDAAIA//z8/wIA//7/AAEDAQEAAP7+//7///0AAgAC/P0AAP7+/wAAAAIC/vz9/v39//39/v39/v0BAgIA/wABAAEBAwP//wAA/Pz7/fn5/P78/fr7////AAD//v8EAgAA/wcMBwICBwn+////AAEBAP7+/f3+//7+/Pr9AP7+AP4AAAAA/Pj6/f4B/vv+//3+/wAAAP8A/wD9/v/+/vv9//7+/v79/vz8/vv9/wAD/gIAAAD+Af8AAAECBAcJBxAM//38/f39/vz8/fv8/gD///7+//0AAAAA/f4AAP///gD9/vv7Cwr+/wAA/v4AAAAAAAAAAQIB/v/+//7+AAEAAAD/+/wAAPz9/vz9//v7/wD/AAABAwD///7//v3///39/f0AAP///wIEAgAA/f3+//7+AAAAAAEB/fv7/fz9/wIJBwoEDBULBQwM/vv+//z7/vz8/v7+/v3/AAAAAwL9/gAAAgQCAP/+AAL9/wD+/fv9///+AwYEAAAA/wEBAP/+/gAEAgUCCRUYDg8L/v0AAgQA/v7/AP//DQ/+/v3+//z9//7///38/v7+AwEDAgAA///9/vz9AAIJBQkJAQD9/vz9/v0AAP7/AgH+/v8B/Pv8/vz9AgMBAAEBBQYDAQIB/v8AAAAAAf//AP7+//8CAgAA/f8BAQAA/v3+//7++/r8/vz8/wD+/fz9AAABAAID/v3/AP7/AAIFAgAA/wAAAP///Pv9//7+//4CAf7+/Pr9/wAAAP/9/v39/v3+/vz8BwMAAAABAwMDAgIB/////wAAAAUBAP7+/Pz+//4A//8AAP7+AP39//v6/v3+//8A/////////f0AAAH///z8/vz+/v7/AAUH//8AAP///v0DAgD//fz+//8A/wIBAAD/AgEGBQEABAQDAgEA/fz+/wD/AAICAQIC/fz7/QEA/fv8/vz9//7/AP7/AP8AAP/+/fv7/fz+AAH+///+/fv8/v39/v/9/v79/v8AAP7+AQcHAgICAQcCAAEB//79/vz8/fv/AAAA/fz+//39AP////8B//z9/wYH/gABAP///wICAAAA/fv9/v7+/fz/AP/+/wACAgUF/fz8/vz+AwUKBgoN/f0AAAD//v79/////fr8/wAD//z8/v7//v/+/////fz+/wAA/fn6/fr7/Pv6/Pr8/P3/AP4AAAD///3+/Pv8/v3+AgD7/fr5//3+/v3+/Pf5/fr8AQQEAgYFAAgLBQMA/Pr8/vz8//79//7+AP79/v39/v79/v39/Pr8/fz+/v4CAwgJ/vz9/////vz+//39AAAAAAME/gMFAgEAAAYDAAgG/vz8/v4A/vz9/vz8/fz9AAAAAAD+//7/AP/9//8A/vr7/vz8/Pn6/fz8AP78/v8A///+/////v39/gD+/fv7/fr8/v8AAAAA/v8CAgMB/wEAAP/+AgL//vz9/wD+//7+/f0AAPz7//7//wIB/Pz+//39AAD/////AQADAv/+/vz7/fr7";
        String feature2 = "SkxUQgkAAAAUAAAAAQAAAF0pml8AAAAAAAAAAAAAAADogDJydQEAAAAGAAAAAAAABAP/AAECEQ4A/wH+/v39//39/f4CAgUH//8AAP79Ag8aEREL/fv8/v3+/v78/v38//7+/wD/+/n7/v////7/AAECAAD/AP8AAAAAAP/+/vz9//3+/vz9/wAA/v4CAgD9/v3+//3+/vv8/f38/v/+//79//4BAgD/Af39/v79///9/v7///3+//7+AAUOCQwL/v39/v39AP/9/vz8AgcGAgAAAPz+AP/9/v3+//79/fz7/fv6A/79/v39/fv+//79/fv8/vz7/vz9/v39/vz+///+//8AAP79Af//AAD//v7+///+AP/8/fz7/Pr8/fz8AAD+/v8A/fn6/Pv6AP7+//////3///38/v3/AP7+/v3+//79//3+//7//vz7/fv7/v////38AQEAAP////7/AP8AAQD//wAA///+/vz8+/n7/fv7/v39/wAAAAT/////BwoFAgYF/v39/v7/AQD9/v78/Pz/AAEA//z8/gAA/Pj5/fz7/vz8/v3+AAD///7/AP8AAAAA///+AAAB/vv9/vz8/v4AAAAAAP/9/v79/wABAQEAAAcNBwQC//39//7//v78/v39/v3+AP8A+/r7/vz9/gAEAwIA/vv8/v7+/wMBAP///v39/v3+AAEJCQ0U+/b4/Pr6AAACAwUG//8AAAACAAIA//78/f3+//3//vz9/v39//7/AAD+/fv9/v39/f38/fv6/Pv9/vz7/Pn6/Pr5/fwAAAD//v0AAP///fv8/v7//wEGBAH+/v39/v7+//4AAP/+AQD/AP8A/gACAQIDAgYCAAIDDQoBAAD//vz9/wAB/v4AAAUO/v7///7+/Pj6/f39///+//7/AAD+/////v3/AAD//v39/v3+AP79/vz7AP8AAAD++vX6/fn5/f8BAAD9///9/v3+/v7+//8AAAACAQEA/Pr8/v3+/v39//7/Av79/v7///z/AAD+/v3+//78/fv7/fv7CA4IAwcI/vz9/v38/v3+//7//v3+/wD9AAAAAAUE//3/AP78AgD+AAEC/v3/AAAB//3+//79/fz/AP////79/vz7//8BAf79AP8AAAAA//8AAQD//v3+/////v79/v39//7+//79/fz9/v7+/v/+//z7AAMDAQEAAAAEAgIB/fz+//38/gADAwgQAAABAwQB/vv9//7//fv8/vz8/vz9//7+AP/+//4AAP79///9/v79/vv8//7+//7+/wD+//39/f8DAwIB/wD+//7+/fv9/v38/v4AAAAA/v4AAP8A/vv7/fz6/vz8/v39/fz9/v/9Av7+//7//v7///7/AP8AAAEA/v3+//4B/vv7/fv6/v7/AP/+AQD//wAA/f/+//8A/f8AAP7+/v7/AP/+/gD///7///7+/v7+//z8/f3+AQMCAgMBAAMEAgD/+vj5/f38/v4AAAIC/v4AAAD/BQQAAP8A/fz8/fz8/Pr9/v39//8AAAAA/fz9/v4A/f39/vv6//3/AAgLAgECAgIC/wD+//7+/wIBAAAB/fv7/v39Av77/fr7/fz9/v7+BAQAAAIDDQ4CAAMFAQD9/v38/v8AAQAAAQP///38/vz9//3+/v0AAP/8/f3+//78/vz+/wAC/fr7/fz8/fz8/v38//0AAAAAAP79/v39AAEEAwQA/fv6/Pr7/PwAAP37/gAAAAD+//7+//7+//3+AAAA/fv9/v79+vr8//38/Pv9/vv7/fz9/v79AP/+//79/v7/AP/9/vz/AAUE/v3+///9+/r+//v5AAD9/v7+AAUEAP39BQUAAP79/v7+///+AAEAAP/+AAD/AP79//7+//79AAD+///+/f3+AAAF/vz9/v/+AwcCAAEA/fv9//3+BQMAAAIC//7+//7+/v0AAP8A/vz+//78/Pj6/v38/v3+//79/v/9/v39+vj6/fz7/v4AAP78AP79/wAABAT+/v7+AAD+/v7///39//7//wIBAQAAAP/+AAME/vv9/wECBgUAAP///fz/AAACAgsFAgD//gD9/v37AgIAAAD///z9/v//+/f6/f//";
        System.out.println(tt.compare(1, feature1, feature2));
    }
}


