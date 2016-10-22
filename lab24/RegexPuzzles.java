import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexPuzzles {
    public static List<String> urlRegex(String[] urls) {
        /* Your code here */
        ArrayList<String> list = new ArrayList<>();
        Pattern malePattern = Pattern.compile("\\(.*?https?:\\/\\/(\\w+?\\.)+?\\w{2,3}\\/\\w+?\\.html.*?\\)");

        for (String string: urls) {
            Matcher matcher = malePattern.matcher(string);
            if(matcher.matches()){
                list.add(string);
            }
        }

        return list;
    }

    public static List<String> findStartupName(String[] names) {
        /* Your code here */
        ArrayList<String> list = new ArrayList<>();
        Pattern malePattern = Pattern.compile("^(Data|App|my|on|un)[a-hj-zA-HJ-Z0-9]+?(ly|sy|ify|\\.io|\\.fm|\\.tv)$");

        for (String string: names) {
            Matcher matcher = malePattern.matcher(string);
            if(matcher.matches()){
                list.add(string);
            }
        }

        return list;
    }

    public static BufferedImage imageRegex(String filename, int width, int height) {
        BufferedReader br;
        int[][][] arr = new int[width][height][3];
        String[] temp;
        String[] temp_pixel;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("No such file found: " + filename);
        }

        // Possible initialization code
        try {
            String line;
            String pixel = "";
            String coor = "";
            while ((line = br.readLine()) != null) {
                // Code for processing each line
                Pattern firstthree = Pattern.compile("(\\[[0-9]*?, [0-9]*?, [0-9]*?\\])");
                Pattern firsttwo = Pattern.compile("(\\([0-9]*?, [0-9]*?\\))");
                Matcher matcherthree = firstthree.matcher(line);
                Matcher matchertwo = firsttwo.matcher(line);

                while(matcherthree.find()){
                    pixel = matcherthree.group(0);
                }

                while(matchertwo.find()){
                    coor = matchertwo.group(0);
                }

                temp = coor.split(",");
                int x = Integer.parseInt(temp[0].replaceAll("[\\[\\(\\)\\]\\,\\ ]", ""));
                int y = Integer.parseInt(temp[1].replaceAll("[\\[\\(\\)\\]\\,\\ ]", ""));

                temp_pixel = pixel.split(",");
                int pixel_x = Integer.parseInt(temp_pixel[0].replaceAll("[\\[\\(\\)\\]\\,\\ ]", ""));
                int pixel_y = Integer.parseInt(temp_pixel[1].replaceAll("[\\[\\(\\)\\]\\,\\ ]", ""));
                int pixel_z = Integer.parseInt(temp_pixel[2].replaceAll("[\\[\\(\\)\\]\\,\\ ]", ""));

                arr[x][y][0] = pixel_x;
                arr[x][y][1] = pixel_y;
                arr[x][y][2] = pixel_z;

            }
        } catch (IOException e) {
            System.err.printf("Input error: %s%n", e.getMessage());
            System.exit(1);
        }

        BufferedImage bf = arrayToBufferedImage(arr);
        return bf;
    }

    public static BufferedImage arrayToBufferedImage(int[][][] arr) {
        BufferedImage img = new BufferedImage(arr.length, arr[0].length, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                int pixel = 0;
                for (int k = 0; k < 3; k++) {
                    pixel += arr[i][j][k] << (16 - 8*k);
                }
                img.setRGB(i, j, pixel);
            }
        }

        return img;
    }

    public static void main(String[] args) {
        /* For testing image regex */
        BufferedImage img = imageRegex("mystery.txt", 400, 400);

        File outputfile = new File("output_img.jpg");
        try {
            ImageIO.write(img, "jpg", outputfile);
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}
