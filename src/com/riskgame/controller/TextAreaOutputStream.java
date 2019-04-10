package com.riskgame.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 * Class to print text in the invoking text area
 * @author Nikitha
 *
 */
public class TextAreaOutputStream extends OutputStream {

   private final JTextArea textArea;
   private final StringBuilder sb = new StringBuilder();

   /**
    * Constructor
    * @param textArea - JTextArea tag
    */
   public TextAreaOutputStream(final JTextArea textArea) {
      this.textArea = textArea;
      sb.append("> ");
   }

   @Override
   public void flush() {
   }

   @Override
   public void close() {
   }

   /**
    * Method to write to text area
    */
   @Override
   public void write(int b) throws IOException {

      if (b == '\r')
         return;

      if (b == '\n') {
         final String text = sb.toString() + "\n";
         SwingUtilities.invokeLater(new Runnable() {
            public void run() {
               textArea.append(text);
            }
         });
         sb.setLength(0);
         sb.append("> ");
         return;
      }

      sb.append((char) b);
   }
}