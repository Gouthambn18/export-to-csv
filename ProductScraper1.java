package jsoupOpenCsv;
import java.io.FileWriter;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class ProductScraper1 {
	


	    public static void main(String[] args) {
	        String url = "https://www.staples.com/Computer-office-desks/cat_CL210795/663ea?icid=BTS:2020:STUDYSPACE:DESKS";
	        String csvFilePath = "product_details.csv";

	        try {
	            FileWriter csvWriter = new FileWriter(csvFilePath);
	            csvWriter.append("Product Name");
	            csvWriter.append(",");
	            csvWriter.append("Product Price");
	            csvWriter.append(",");
	            csvWriter.append("Item Number/SKU/Product Code");
	            csvWriter.append(",");
	            csvWriter.append("Model Number");
	            csvWriter.append(",");
	            csvWriter.append("Product Category");
	            csvWriter.append(",");
	            csvWriter.append("Product Description");
	            csvWriter.append("\n");

	            Document document = Jsoup.connect(url).get();
	            Elements products = document.select(".card__body");

	            for (Element product : products) {
	                String name = product.select(".card__title").text();
	                String price = product.select(".card__price").text();
	                String itemNumber = product.select(".card__sku").text();
	                String modelNumber = product.select(".card__model").text();
	                String category = product.select(".breadcrumb__item a").last().text();
	                String description = product.select(".card__description").text();

	                csvWriter.append(name);
	                csvWriter.append(",");
	                csvWriter.append(price);
	                csvWriter.append(",");
	                csvWriter.append(itemNumber);
	                csvWriter.append(",");
	                csvWriter.append(modelNumber);
	                csvWriter.append(",");
	                csvWriter.append(category);
	                csvWriter.append(",");
	                csvWriter.append(description);
	                csvWriter.append("\n");
	            }

	            csvWriter.flush();
	            csvWriter.close();
	            System.out.println("Product details have been exported to " + csvFilePath);

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}


