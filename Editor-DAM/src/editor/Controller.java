package editor;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;


public class Controller {
    public TextArea text;
    public MenuItem miOT12;
    public MenuItem miOT10;
    public MenuItem miFSortir;
    public MenuItem miECopiar;
    public MenuItem miETallar;
    public MenuItem miEPaste;
    public MenuItem miEUndo;
    public Button btCopy;
    public MenuItem miAAbout;
    public AnchorPane mainPane;
    public CheckMenuItem cmiOFSans;
    public CheckMenuItem cmiOFFree;
    public MenuItem miCCopiar;
    public MenuItem miCTallar;


    File currentSaveFile;



    /**
     * S'executa al iniciar l'aplicació.
     */
    public void initialize(){
        btCopy.setGraphic(new ImageView("copy.png"));
        cmiOFSans.setSelected(true);

    }

    /**
     * Menú Opcions -> Font. Canvi de la font del text.
     * @param actionEvent Event onAction de tots els MenuItem
     */
    public void setFont(ActionEvent actionEvent) {
        CheckMenuItem item = (CheckMenuItem) actionEvent.getSource();
        cmiOFFree.setSelected(false);
        cmiOFSans.setSelected(false);
        item.setSelected(true);
        text.setFont(new Font(item.getText(), text.getFont().getSize()));
    }

    /**
     * Menú Opcions -> Tamany. Canvi del tamany de la font del text.
     * @param actionEvent Event onAction de tots els MenuItem
     */
    public void setFontSize(ActionEvent actionEvent) {
        double fontSize = Double.parseDouble(((MenuItem) actionEvent.getSource()).getText());
        text.setFont(new Font(fontSize));
    }

    /**
     * Menú Fitxer -> Sortir. Surt de l'aplicació
     * @param actionEvent Event onAction de tots els MenuItem
     */
    public void sortir(ActionEvent actionEvent) {
        Platform.exit();
    }

    /**
     * Menú Editar -> Copiar. Còpia el text seleccionat.
     * @param actionEvent Event onAction de tots els MenuItem
     */
    public void eCopiar(ActionEvent actionEvent) {
        text.copy();
    }

    /**
     * Menú Editar -> Tallar. Talla el text seleccionat.
     * @param actionEvent Event onAction de tots els MenuItem
     */
    public void eTallar(ActionEvent actionEvent) {
        text.cut();
    }

    /**
     * Menú Editar -> Enganxar. Enganxa el text del clipboard.
     * @param actionEvent Event onAction de tots els MenuItem
     */
    public void ePaste(ActionEvent actionEvent) {
        text.paste();
    }

    /**
     * Menú Editar -> Desfer. Desfà la última acció.
     * @param actionEvent Event onAction de tots els MenuItem
     */
    public void eUndo(ActionEvent actionEvent) {
        text.undo();
    }

    /**
     * Menú Ajuda -> About. Mostra un diàleg modal amb info de la app.
     *
     * @param actionEvent Event onAction del MenuItem
     */
    public void eAbout(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cutreditor");
        alert.setHeaderText("Un editor de text cutre!");
        alert.setContentText("Fet durant el curs 2015-2016.");

        alert.showAndWait();
    }

    /**
     * Deshabilita els items Copiar i Tallar del menú Editar si no hi ha text seleccionat.
     *
     * @param event Event onShowing del menú Editar.
     */
    public void deshabilitarCTEd(Event event) {
        if (text.getSelectedText().equals("")) {
            miECopiar.setDisable(true);
            miETallar.setDisable(true);
        } else {
            miECopiar.setDisable(false);
            miETallar.setDisable(false);
        }
    }

    /**
     * Deshabilita els items Copiar i Tallar del menú contextual si no hi ha text seleccionat.
     *
     * @param event Event onShowing del menú Editar.
     */
    public void deshabilitarCTCont(Event event) {
        if (text.getSelectedText().equals("")) {
            miCCopiar.setDisable(true);
            miCTallar.setDisable(true);
        } else {
            miCCopiar.setDisable(false);
            miCTallar.setDisable(false);
        }
    }

    public void obrir(ActionEvent actionEvent) {

        final FileChooser fileChooser = new FileChooser();
        final File file = fileChooser.showOpenDialog(mainPane.getScene().getWindow());

        if(file!=null){
            FileReader fr = null;
            BufferedReader br = null;
            String texto = "";
            try {
                fr = new FileReader(file);
                br = new BufferedReader(fr);
                String st = br.readLine();
                while (st != null) {
                    texto = texto + st + "\n";
                    st = br.readLine();
                }
            } catch (Exception e) {
                text.appendText(e.toString());
            } finally {
                try {
                    fr.close();
                } catch (Exception e2) {
                    text.appendText(e2.toString());
                }
            }
            text.appendText(texto);
        }

    }

    public void guardar(ActionEvent actionEvent) {
        final FileChooser fileChooser = new FileChooser();
        final File file = fileChooser.showSaveDialog(mainPane.getScene().getWindow());
        if(file!=null){
            FileWriter fw = null;
            BufferedWriter bw = null;
            try {
                // EL segundo parametro es un boolean
                // En true escribe al final
                // En false escribe al inicio
                fw = new FileWriter(file, false);
                bw = new BufferedWriter(fw);

                String texto = text.getText();
                bw.write(texto, 0, texto.length());
            } catch (Exception e) {
                text.appendText(e.toString());
            } finally {
                try {
                    bw.close();
                } catch (Exception e2) {
                    text.appendText(e2.toString());
                }
            }
        }
    }


}
