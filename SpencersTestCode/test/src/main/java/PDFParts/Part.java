package PDFParts;

import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;

public class Part {
    private int partNum;
    private String description;
    private int quantity;
    private double price;
    private double disc;
    private double total;
    public Part(int partNum , String description , int quantity , double price , double disc , double total  ){
        this.partNum = partNum;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
    }
    public  Cell[] createInvoiceFormatCells(){
        Cell partCell = new Cell(1,1)
                .setBorder(Border.NO_BORDER)
                .add(new Paragraph(String.valueOf(partNum)));
        Cell descriptionCell = new Cell(1,1)
                .setBorder(Border.NO_BORDER)
                .add(new Paragraph(description));
        Cell quantityCell = new Cell(1,1)
                .setBorder(Border.NO_BORDER)
                .add(new Paragraph(String.valueOf(quantity)));
        Cell priceCell = new Cell(1,1)
                .setBorder(Border.NO_BORDER)
                .add(new Paragraph(String.valueOf(price) ));
        Cell discCell = new Cell(1,1)
                .setBorder(Border.NO_BORDER)
                .add(new Paragraph(String.valueOf(disc)));
        Cell totalCell = new Cell(1,1)
                .setBorder(Border.NO_BORDER)
                .add(new Paragraph(String.valueOf(total)));
        return new Cell[]{partCell, descriptionCell, quantityCell, priceCell, discCell, totalCell};
    }


}
