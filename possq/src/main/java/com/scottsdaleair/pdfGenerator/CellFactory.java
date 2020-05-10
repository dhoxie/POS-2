package com.scottsdaleair.pdfGenerator;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.*;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.Calendar;

public class CellFactory{
	public Cell getColon( ){
		return new Cell(1,1)
                .setTextAlignment(TextAlignment.CENTER)
                .setBorder(Border.NO_BORDER)
                .add(new Paragraph(":"))
                .setWidth(1);
	}
}