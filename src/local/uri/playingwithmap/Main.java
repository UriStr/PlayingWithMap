package local.uri.playingwithmap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {


    public static void main(String[] args) {
        Map<String, Map<String, Long>> resultMap = new TreeMap<>();
        List<String> strings = new ArrayList<>();

        try {
            Scanner newscan = new Scanner(new File("D:\\INPUT\\test.txt"));
            newscan.useDelimiter(System.getProperty("line.separator"));


            while (newscan.hasNextLine()) {
                String text = newscan.nextLine();
                strings.add(text);
            }

            for (String str : strings) {
                String[] value = str.split(" ");

                Data data = new Data(value[0], value[1], Long.parseLong(value[2]));
                if (resultMap.containsKey(data.getProvider())) {
                    Map<String, Long> flowersForCurrentProvider = resultMap.get(data.getProvider());
                    if (flowersForCurrentProvider.containsKey(data.getValue())) {
                        flowersForCurrentProvider.put(data.getValue(), flowersForCurrentProvider.get(data.getValue()) + data.getCount());
                    } else {
                        flowersForCurrentProvider.put(data.getValue(), data.getCount());
                    }
                } else {
                    Map<String, Long> map = new TreeMap<>();
                    map.put(data.getValue(), data.getCount());
                    resultMap.put(data.getProvider(), map);
                }
            }
            for (String str : resultMap.keySet()) {
                System.out.println(str + ":");

                for (String key : resultMap.get(str).keySet()) {
                    System.out.print(key + " ");
                    System.out.println(resultMap.get(str).get(key));

                }
            }
            newscan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static class Data {
        private String provider;
        private String value;
        private long count;

        public Data(String provider, String value, long count) {
            this.provider = provider;
            this.value = value;
            this.count = count;
        }

        public String getValue() {
            return value;
        }

        public long getCount() {
            return count;
        }

        public String getProvider() {
            return provider;
        }

    }
}