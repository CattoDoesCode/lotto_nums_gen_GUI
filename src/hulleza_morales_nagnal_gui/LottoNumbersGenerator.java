package hulleza_morales_nagnal_gui;

import java.util.ArrayList; // import the ArrayList class

public class LottoNumbersGenerator {

    public static String generate(int limit, int range) {
        ArrayList<Integer> lotto_nums = new ArrayList<>();

        while (true) {
            if (lotto_nums.size() == limit) {
                break;
            }
            else {

                while (true) {
                    int rand_num = (int) (Math.random() * range) + 1;

                    boolean is_present = lotto_nums.contains(rand_num);

                    if(!is_present) {
                        lotto_nums.add(rand_num);
                        break;
                    }
                }

            }
        }

        // converting arraylist to string
        StringBuilder str_lottoNums = new StringBuilder();
        for (int i = lotto_nums.size() - 1; i >= 0; i--) {
            int num = lotto_nums.get(i);
            str_lottoNums.append(num).append("  ");
        }

        return str_lottoNums.toString();

    }
}
