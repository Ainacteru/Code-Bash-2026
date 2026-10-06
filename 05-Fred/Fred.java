
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Fred {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("./fred.dat"));

        int programs = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < programs; i++) {
            int ops_num = sc.nextInt();
            sc.nextLine();

            int[] regs = new int[26];
            String[] ops = new String[ops_num];

            for (int j = 0; j < ops_num; j++) {
                ops[j] = sc.nextLine();
            }

            for (int j = 0; j < ops.length; j++) {


                int num = parseFred(ops[j], regs);
                if (num != -1)
                    j = num;
            }
        }
    }

    private static int parseFred(String operation, int[] regs) {

        String[] op = operation.split(" ");
        System.out.println(Arrays.toString(op));
        switch (op[0]) {
            case "SET" -> {
                int set_reg = op[1].charAt(0) - 'a';
                int set_int = op[2].charAt(0) - '0';

                regs[set_reg] = set_int;
            }

            case "ADD" -> {
                int set_addx = op[1].charAt(0) - 'a';
                String set_addy = op[2];

                if (set_addy.matches("[0-9]+")) {
                    regs[set_addx] += Integer.parseInt(set_addy);
                } else {
                    regs[set_addx] += regs[set_addy.charAt(0) - 'a'];
                }
            }

            case "SUB" -> {
                int set_subx = op[1].charAt(0) - 'a';
                String set_suby = op[2];

                if (set_suby.matches("[0-9]+")) {
                    regs[set_subx] -= Integer.parseInt(set_suby);
                } else {
                    regs[set_subx] -= regs[set_suby.charAt(0) - '0'];
                }
            }

            case "JMP" -> {
                return Integer.parseInt(op[1] + 1);
            }

            case "JZ" -> {
                
                int jz_x = op[1].charAt(0) - 'a';
                if (regs[jz_x] == 0)
                    return Integer.parseInt(op[2] + 1);
            }

            case "PRINT" -> {
                int print_reg = op[1].charAt(0) - 'a';
                
                System.out.println(regs[print_reg]);
            }

            case "HALT" -> {
                break;
            }

            default -> throw new AssertionError();
        }
        return -1;
    }
}