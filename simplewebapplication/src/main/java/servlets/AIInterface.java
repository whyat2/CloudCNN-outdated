package servlets;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;

import javax.imageio.ImageIO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import net.coobird.thumbnailator.Thumbnails;

@WebServlet(name = "AIInterface", urlPatterns = "/AI") //urlPatterns = {"/x/* */", "/y", "/x/y"} allows multiple
@MultipartConfig(
    //location = "/uploads",
    fileSizeThreshold = 1024 * 1024, //1MiB
    maxFileSize = 1024 * 1024 * 10,     //10MiB
    maxRequestSize= 1024 * 1024 * 11 //11MiB
)
public class AIInterface extends HttpServlet{
    HttpClient client = HttpClient.newHttpClient();
    String LOCATION_ID = "us-central1"; //old API info went here
    String ENDPOINT_ID=""; 
    String PROJECT_ID="";
    final private int IMAGE_LENGTH_WIDTH = 125;
    private byte[] pixels;
    @Override
    public void init() throws ServletException{
        super.init();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            Part part = request.getPart("file");
            InputStream x = part.getInputStream();
            BufferedImage image = ImageIO.read(x);
            image = Thumbnails.of(image).size(IMAGE_LENGTH_WIDTH, IMAGE_LENGTH_WIDTH).asBufferedImage();
            ByteArrayOutputStream resizedStream = new ByteArrayOutputStream();
            ImageIO.write(image, "png", resizedStream);
            x = new ByteArrayInputStream(resizedStream.toByteArray());
            System.out.println(image.toString());
            pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
            //System.out.print(Arrays.toString(x.readAllBytes()) + " ");
            boolean hasAlphaChannel = image.getAlphaRaster() != null;
            int pixelLength = 3;
            if (hasAlphaChannel) {
                pixelLength = 4;
            }
            int [][][] tensor = toRGBPixels(pixelLength, hasAlphaChannel);
            //System.out.println(Arrays.toString(tensor[0][0]));
            int[][][][] ThingToPass = {tensor};

            //Create Request
            //System.out.println(Arrays.deepToString(ThingToPass));
            String requestInfo = Arrays.deepToString(ThingToPass);
            String body = "{\n\"instances:\" " + requestInfo + "}";
            String urlString = "https://" + LOCATION_ID + "-aiplatform.googleapis.com/v1/projects/" + PROJECT_ID;
            urlString += "/locations/" + LOCATION_ID + "/endpoints/" + ENDPOINT_ID + ":predict";
            HttpRequest predictionHttpRequest = HttpRequest.newBuilder().uri(URI.create(urlString)).POST(
                HttpRequest.BodyPublishers.ofString(body)
            ).build();
            //Optional<BodyPublisher> body = new HttpRequest.BodyPublisher();
            HttpResponse<String> predictionResponse = client.send(predictionHttpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(predictionResponse);
            
        } catch(Exception e){
            System.err.println("SomethngWrongFileUpload");
        }
        response.getWriter().println("test file upload");
        getServletContext().getRequestDispatcher("/TestClouds.jsp").forward(request, response);
    }
    private String getFileName(Part part){
        String contentStuff = part.getHeader("content-disposition");
        if(!contentStuff.contains("filename=")){
            return null;
        }
        int startIndex = contentStuff.indexOf("filename=") + 10;
        int endingIndex = contentStuff.length() - 1;
        return contentStuff.substring(startIndex, endingIndex);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        getServletContext().getRequestDispatcher("/TestClouds.jsp").forward(request, response);
    }
    private int getRGB(int x, int y, int pixelLength, boolean hasAlphaChannel) {
        int position = (y * pixelLength * IMAGE_LENGTH_WIDTH) + (x * pixelLength);
        int argb = -16777216; // 255 alpha
        if (hasAlphaChannel) {
            argb = (((int) pixels[position++] & 0xff) << 24); // alpha
        }

        argb += ((int) pixels[position++] & 0xff); // blue
        argb += (((int) pixels[position++] & 0xff) << 8); // green
        argb += (((int) pixels[position++] & 0xff) << 16); // red
        return argb;
    }
    private int[] getRGB2(int x, int y, int pixelLength, boolean hasAlphaChannel) {
        int color = getRGB(x, y, pixelLength, hasAlphaChannel);
        int red = ((color & 0xff0000) >> 16);
        int green = ((color & 0xff00) >> 8);
        int blue = (color & 0xff);
        return new int[]{red, green, blue};
    }
    public int[][][] toRGBPixels(int pixelLength, boolean hasAlphaChannel) {
        int[][][] tensor = new int[IMAGE_LENGTH_WIDTH][][];
        for (int y = 0; y < IMAGE_LENGTH_WIDTH; y++) {
            int[][] width = new int[IMAGE_LENGTH_WIDTH][];
            for (int x = 0; x < IMAGE_LENGTH_WIDTH; x++) {
                int[] rgb = getRGB2(x, y, pixelLength, hasAlphaChannel);
                width[x] = rgb;
            }
            tensor[y] = width;
        }
        return tensor;
    }
}
