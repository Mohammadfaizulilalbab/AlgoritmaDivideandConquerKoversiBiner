import java.util.Scanner;

public class DecimalToBinaryConverter {

    /**
     * Konversi desimal ke biner menggunakan algoritma divide and conquer
     * @param decimal Angka desimal yang akan dikonversi
     * @return String representasi biner
     */
    public static String decimalToBinary(int decimal) {
        // Base case: jika angka 0 atau 1
        if (decimal == 0) {
            return "0";
        }
        if (decimal == 1) {
            return "1";
        }

        // Divide: bagi angka dengan 2
        int quotient = decimal / 2;
        int remainder = decimal % 2;

        // Conquer: panggil rekursif untuk quotient
        // Combine: gabungkan hasil rekursif dengan remainder
        return decimalToBinary(quotient) + remainder;
    }

    /**
     * Versi alternatif dengan StringBuilder untuk efisiensi
     * @param decimal Angka desimal yang akan dikonversi
     * @return String representasi biner
     */
    public static String decimalToBinaryOptimized(int decimal) {
        if (decimal == 0) {
            return "0";
        }

        StringBuilder binary = new StringBuilder();
        divideAndConquer(decimal, binary);
        return binary.reverse().toString();
    }

    /**
     * Helper method untuk versi optimized
     * @param decimal Angka desimal
     * @param binary StringBuilder untuk menyimpan hasil
     */
    private static void divideAndConquer(int decimal, StringBuilder binary) {
        // Base case
        if (decimal == 0) {
            return;
        }

        // Divide: bagi dengan 2
        int quotient = decimal / 2;
        int remainder = decimal % 2;

        // Simpan remainder
        binary.append(remainder);

        // Conquer: panggil rekursif
        divideAndConquer(quotient, binary);
    }

    /**
     * Method untuk menampilkan proses konversi step by step
     * @param decimal Angka desimal
     * @param depth Kedalaman rekursi untuk indentasi
     * @return String representasi biner
     */
    public static String decimalToBinaryWithSteps(int decimal, int depth) {
        // Indentasi untuk visualisasi
        String indent = "  ".repeat(depth);

        System.out.println(indent + "Konversi: " + decimal);

        // Base case
        if (decimal == 0) {
            System.out.println(indent + "Base case: 0 -> return \"0\"");
            return "0";
        }
        if (decimal == 1) {
            System.out.println(indent + "Base case: 1 -> return \"1\"");
            return "1";
        }

        // Divide
        int quotient = decimal / 2;
        int remainder = decimal % 2;

        System.out.println(indent + "Divide: " + decimal + " / 2 = " + quotient +
                " remainder " + remainder);

        // Conquer
        String result = decimalToBinaryWithSteps(quotient, depth + 1);

        // Combine
        String finalResult = result + remainder;
        System.out.println(indent + "Combine: \"" + result + "\" + " + remainder +
                " = \"" + finalResult + "\"");

        return finalResult;
    }

    /**
     * Method untuk validasi hasil dengan konversi balik
     * @param binary String biner
     * @return Nilai desimal
     */
    public static int binaryToDecimal(String binary) {
        int decimal = 0;
        int power = 0;

        // Proses dari kanan ke kiri
        for (int i = binary.length() - 1; i >= 0; i--) {
            if (binary.charAt(i) == '1') {
                decimal += Math.pow(2, power);
            }
            power++;
        }

        return decimal;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== KONVERSI DESIMAL KE BINER ===");
        System.out.println("Menggunakan Algoritma Divide and Conquer\n");

        while (true) {
            System.out.print("Masukkan angka desimal (0 untuk keluar): ");
            int decimal = scanner.nextInt();

            if (decimal == 0) {
                System.out.println("Terima kasih!");
                break;
            }

            if (decimal < 0) {
                System.out.println("Mohon masukkan angka positif!\n");
                continue;
            }

            System.out.println("\n--- Hasil Konversi ---");

            // Konversi biasa
            String binary1 = decimalToBinary(decimal);
            System.out.println("Hasil (method 1): " + decimal + " -> " + binary1);

            // Konversi optimized
            String binary2 = decimalToBinaryOptimized(decimal);
            System.out.println("Hasil (method 2): " + decimal + " -> " + binary2);

            // Validasi
            int validation = binaryToDecimal(binary1);
            System.out.println("Validasi: " + binary1 + " -> " + validation);
            System.out.println("Validasi " + (validation == decimal ? "BERHASIL" : "GAGAL"));

            // Tampilkan proses step by step
            System.out.println("\n--- Proses Step by Step ---");
            String binarySteps = decimalToBinaryWithSteps(decimal, 0);

            System.out.println("\n" + "=".repeat(50) + "\n");
        }

        scanner.close();
    }
}
