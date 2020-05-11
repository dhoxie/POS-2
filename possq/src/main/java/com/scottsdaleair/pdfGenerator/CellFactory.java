package com.scottsdaleair.pdfGenerator;

import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;

public class CellFactory {

  /**
   * Creates a generic cell.
   * @return A generic cell
   */
  public Cell getColon() {
    return new Cell(1, 1)
      .setTextAlignment(TextAlignment.CENTER)
      .setBorder(Border.NO_BORDER)
      .add(new Paragraph(":"))
      .setWidth(1);
  }
}