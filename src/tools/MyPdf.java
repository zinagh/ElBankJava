package tools;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Utilisateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


public class MyPdf {

    private static final Font FONT_TITLE = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private static  final Font FONT_SUBTITLE = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
    private static  final Font FONT_TABLE_HEADER = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
    private static  final Font FONT_TABLE_CELL = new Font(Font.FontFamily.TIMES_ROMAN, 12);

        public static void generate(List <Utilisateur> userList, String filename) throws IOException, DocumentException {


            // Create the PDF document
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            File pdfDir = new File("pdfs");
            pdfDir.mkdir();
            PdfWriter.getInstance(document, new FileOutputStream("pdfs/" + filename + ".pdf"));
            document.open();

            // Add title
            Paragraph title = new Paragraph("Liste des utilisateurs", FONT_TITLE);
            title.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(title);

            // Add subtitle
            Paragraph subtitle = new Paragraph("Liste des utilisateurs enregistrés dans le système", FONT_SUBTITLE);
            subtitle.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(subtitle);

            // Add table
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.setSpacingBefore(20);
            table.setSpacingAfter(20);

            // Add table header
            addTableHeader(table);

            // Add table data
            addTableData(table, userList);
            document.add(table);
            document.close();
        }
            private static void addTableHeader (PdfPTable table) {
                PdfPCell cell;

                cell = new PdfPCell(new Paragraph("CIN", FONT_TABLE_HEADER));
                cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("Nom", FONT_TABLE_HEADER));
                cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("Prénom", FONT_TABLE_HEADER));
                cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("Date de naissance", FONT_TABLE_HEADER));
                cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("Email", FONT_TABLE_HEADER));
                cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                table.addCell(cell);


            }
    private static void addTableData(PdfPTable table, List<Utilisateur> userList) {
        for (Utilisateur user : userList) {
            table.addCell(String.valueOf(user.getCin_u()));
            table.addCell(user.getNom_u());
            table.addCell(user.getPrenom_u());
            table.addCell(user.getDate_naissance().toString());
            table.addCell(user.getEmail_u());
        }
    }




}




