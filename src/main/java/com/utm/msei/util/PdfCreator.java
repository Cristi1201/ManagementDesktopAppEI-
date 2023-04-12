package com.utm.msei.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.utm.msei.controllers.administration.actions.OrarHandler;
import com.utm.msei.persistence.dto.ClasaDto;
import javafx.collections.ObservableList;

import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PdfCreator {

    public static String createPdfOrar(ObservableList<OrarHandler.OrarTable> orarTable, ClasaDto clasaDto) {
        Document document = new Document(PageSize.A4.rotate());

        String pdfName;
        try {
            pdfName = "Orar_clasa_" + clasaDto.getNume() + ".pdf";
            Path downloadsDir = Paths.get(System.getProperty("user.home"), "Downloads");
            Path filePath = downloadsDir.resolve(pdfName);
            pdfName = filePath.toFile().getAbsolutePath();

            PdfWriter.getInstance(document, new FileOutputStream(pdfName));
            document.open();

            Font fontHeader = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
            Font fontRow = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL);

            PdfPTable table = new PdfPTable(6);
            List<String> headers = new ArrayList<>(List.of("Durata / Zile", "Luni", "Marti", "Miercuri", "Joi", "Vineri"));
            for (String header : headers) {
                PdfPCell cell = new PdfPCell();
                cell.setGrayFill(0.9f);
                cell.setPhrase(new Phrase(header.toUpperCase(), fontHeader));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setPadding(8f);
                table.addCell(cell);
            }
            table.completeRow();

            Font fontBold = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
            Font fontRegular = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);

            List<List<OrarHandler.OrarTable>> orar = OrarHandler.sortByDurataAndZi(orarTable);
            for (List<OrarHandler.OrarTable> discForDurata : orar) {
                boolean durataWasSeted = false;
                for (OrarHandler.OrarTable discs : discForDurata) {
                    // durata
                    if (!durataWasSeted) {
                        Phrase phrase = new Phrase(discs.getDurata(), fontRow);
                        PdfPCell cell = new PdfPCell(phrase);
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setPadding(10f);
                        table.addCell(cell);
                        durataWasSeted = true;
                    }
                    // data
                    String cellText = discs.getDiscProf();

                    if (cellText == null || cellText.isEmpty()) {
                        Phrase phrase = new Phrase(discs.getDiscProf(), fontRow);
                        PdfPCell cell = new PdfPCell(phrase);
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setPadding(10f);
                        table.addCell(cell);
                    } else {
                        int spaceIndex;
                        spaceIndex = cellText.indexOf(',');

                        String firstWord = cellText.substring(0, spaceIndex);
                        String remainingText = cellText.substring(spaceIndex + 1);

                        Phrase phraseBold = new Phrase(firstWord, fontBold);
                        Phrase phraseRegular = new Phrase(remainingText, fontRegular);

                        PdfPCell cell = new PdfPCell();
                        cell.addElement(phraseBold);
                        cell.addElement(phraseRegular);
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setPadding(5f);
                        table.addCell(cell);
                    }
                }
                table.completeRow();
            }

            Paragraph title = new Paragraph("Orar clasa " + clasaDto.getNume(), new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD));
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(50f);
            document.add(title);
            document.add(table);

        } catch (Exception e) {
            return null;
        } finally {
            document.close();
        }

        return pdfName;
    }
}
