package edu.curso;

import java.time.format.DateTimeFormatter;

import edu.curso.model.Aviso;
import edu.curso.model.Equipamento;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TelaAdminAluno extends Application {

    private Stage stage;
    private TabPane tabPane;
    private boolean admin = true;
    
    TextField txtTituloAviso = new TextField();
    TextArea txtDescricaoAviso = new TextArea();
    TextField txtNomeEquipamento = new TextField();
    TextField txtDescricaoEquip = new TextField();
    TextField txtBuscaEquipamento = new TextField();
    
    Button btnPesquisarAviso = new Button("PESQUISAR");
    Button btnSalvarAviso = new Button("PUBLICAR");
    
    TableView<Aviso> tableAvisos = new TableView<>();
    TableView<Equipamento> tableEquipamentos = new TableView<>();
    
    ControleAviso controlAviso = new ControleAviso();

    public void start(Stage stage) throws Exception {

        if (admin) {

            try {
                this.stage = stage;
                stage.setTitle("MadFit - ADMIN");
                BorderPane root = new BorderPane();
                Scene scene = new Scene(root, 800, 500);
                
                tabPane = new TabPane();

                // ABAS DA TELA DE ADMIN
                Tab tabInicio = new Tab("Inicio");
                
                /*
        ****************** CADASTRO DE AVISOS ******************
                  */
                
                Tab tabAvisos = new Tab("Avisos");
                GridPane gridAvisos = new GridPane();
                
                btnPesquisarAviso.setOnAction( e -> controlAviso.pesquisarAviso());
                btnSalvarAviso.setOnAction( e -> controlAviso.cadastrarAviso());
                
                txtDescricaoAviso.setMaxHeight(100);
                txtDescricaoAviso.setMaxWidth(400);
                gridAvisos.setVgap(25);
                gridAvisos.setHgap(10);
                
                gridAvisos.add(new Label("Manutenção de Avisos"), 0, 0);
                gridAvisos.add(new Label("Título"), 0, 1);
                gridAvisos.add(txtTituloAviso, 1, 1);
                gridAvisos.add(btnPesquisarAviso, 2, 1);
                gridAvisos.add(new Label("Descrição"), 0, 2);
                gridAvisos.add(txtDescricaoAviso, 1, 2);
                gridAvisos.add(btnSalvarAviso, 2, 2);
                gridAvisos.add(tableAvisos, 1, 3);
                
                bindingAvisos();
                createColumnsAviso();
                
                tabAvisos.setContent(gridAvisos);
                
                
                
                
                Tab tabEventos = new Tab("Aulas");
                
                
                    //TELA DE EQUIPAMENTOS - ADMIN
                Tab tabEquipamentos = new Tab("Equipamentos");
                GridPane gridEquip = new GridPane();
                
                Button btnPesquisarEquip = new Button("PESQUISAR");
                Button btnSalvarEquip = new Button("PUBLICAR");
                
                gridEquip.add(new Label("Manutenção de Equipamentos"), 0, 0);
                gridEquip.add(new Label("Equipamento"), 0, 1);
                gridEquip.add(txtNomeEquipamento, 1, 1);
                gridEquip.add(btnPesquisarEquip, 2, 1);
                gridEquip.add(new Label("Descrição"), 0, 2);
                gridEquip.add(txtDescricaoEquip, 1, 2);
                
                txtNomeEquipamento.setMinWidth(400);

                gridEquip.add(btnSalvarEquip, 2, 2);
                gridEquip.setVgap(25);
                gridEquip.setHgap(10);
                
                createColumnsEquip();
                gridEquip.add(tableEquipamentos, 1, 3);
                tabEquipamentos.setContent(gridEquip);

                
                Tab tabAlunos = new Tab("Alunos");
                Tab tabTutores = new Tab("Tutores");
                Tab tabPlano = new Tab("Planos");
                	
                tabInicio.setClosable(true);
                tabAvisos.setClosable(false);
                tabEventos.setClosable(false);
                tabEquipamentos.setClosable(false);
                tabAlunos.setClosable(false);
                tabTutores.setClosable(false);
                tabPlano.setClosable(false);
                
                tabPane.getTabs().addAll(tabAvisos, tabEventos, tabEquipamentos, tabAlunos, tabTutores, tabPlano);
                root.setCenter(tabPane);

                scene.getStylesheets().add(TelaAdminAluno.class.getResource("estilo.css").toExternalForm());
                stage.setScene(scene);
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void bindingAvisos() {
    	Bindings.bindBidirectional(txtTituloAviso.textProperty(), controlAviso.tituloProperty());
    	Bindings.bindBidirectional(txtDescricaoAviso.textProperty(), controlAviso.descricaoProperty());
		
	}

	private void createColumnsEquip() {
    	TableColumn <Equipamento, String> colNomeEquip = new TableColumn<>("Equipamento");
    	TableColumn <Equipamento, String> colDescricaoEquip = new TableColumn<>("Descrição do Equipamento");
		tableEquipamentos.getColumns().addAll(colNomeEquip, colDescricaoEquip);
	}

	private void createColumnsAviso() {
		
		tableAvisos.setItems(controlAviso.getListaAvisos());
		
		TableColumn <Aviso, String> colTituloAviso = new TableColumn<>("Título do Aviso");
		colTituloAviso.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getTituloAviso()));
		
		TableColumn <Aviso, String> colDescricaoAviso = new TableColumn<>("Descrição");
		colDescricaoAviso.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getDescricaoAviso()));
		tableAvisos.getColumns().addAll(colTituloAviso, colDescricaoAviso);
	}

	public static void main(String[] args) {
        Application.launch(TelaAdminAluno.class, args);
    }
}
