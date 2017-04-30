package graphic;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import connection.Query;
import regras.Inscricao;

public final class Janela extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private static ImageIcon torch = new ImageIcon(resizeImage("images/torch.png"));;
	private static ImageIcon olympic = new ImageIcon(resizeImage("images/olympic.png"));
	private static JButton sendModalidade = new JButton("Cadastrar Modalidade");
	private static JButton sendTornament = new JButton("Cadastrar Torneio");
	private static JButton sendPlayer = new JButton("Cadastrar Competidor");
	private static JButton getPlayerTorneio = new JButton("Cadastrar Torneio Desejado");
	private static JButton cadastrarMarca = new JButton("Cadastrar marca");
	private static JButton cadastrarData = new JButton("Cadastrar data");
	private static JButton sendQuery1 = new JButton("Send");
	private static JButton sendQuery2 = new JButton("Send");
	private static JButton sendQuery3 = new JButton("Send");
	private static JButton endEpocaParaCadastro = new JButton("Acabar o tempo de cadastro");
	private static JButton returnModalidade = new JButton("Inserir outra modalidade");
	private static JButton endCadastro = new JButton("Acabar Cadastro");
	private static JButton sendPlayerModalidade = new JButton("Escolher Modalidade");
	private static JComponent Principal;
	private static JTabbedPane tabbedPane = new JTabbedPane();
	private static JTabbedPane subPane = new JTabbedPane();
	private static Vector<JTextField> v1 = new TextFields(2).getVectorText();
	private static Vector<JTextField> v2 = new TextFields(2).getVectorText();
	private static Vector<JTextField> v3 = new TextFields(4).getVectorText();
	private static Vector<JTextField> v5 = new TextFields(1).getVectorText();
	private static JTextField notaSerie = new JTextField();
	private static JTextField dataSerie = new JTextField();
	private static JTextField Q1 = new JTextField();
	private static JTextField Q2 = new JTextField();
	private static JTextField Q3 = new JTextField();
	private static String [] importantInfo = new String [3];
	private static String modalidadesData;
	private static String [][] importantInfoTorneio;
	private static String [][] participanteStrings;
	private static String tempNomeModalidade = null;
	private static String tempSeriesModalidade;
	private static Query initQuery;
	private static Inscricao newPlayer;
	@SuppressWarnings("rawtypes")
	private static JComboBox BoxModalidadeList;
	@SuppressWarnings("rawtypes")
	private static JComboBox BoxTornamentList;
	@SuppressWarnings("rawtypes")
	private static JComboBox BoxModalidadeAdmnistrantionList;
	@SuppressWarnings("rawtypes")
	private static JComboBox BoxModalidadeSerieList;
	@SuppressWarnings("rawtypes")
	private static JComboBox boxModalidadeDataList;
	@SuppressWarnings("rawtypes")
	private static JComboBox boxParticipanteSerieList;
    private static JRadioButton Feminino,femininoTornamentAdministracao;
    private static JRadioButton Masculino,masculinoTornamentAdministracao;
    private static JRadioButton femininoSeries;
    private static JRadioButton masculinoSeries;
    private static JRadioButton eliminatoria;
    private static JRadioButton semifinal;
    private static JRadioButton finalmatchs;
    private static JRadioButton FemininoPlayerChoice;
    private static JRadioButton MasculinoPlayerChoice;
    private static JRadioButton eliminatoriaData;
    private static JRadioButton semifinalData;
    private static JRadioButton finalmatchsData;
    private static JRadioButton femininoData;
    private static JRadioButton masculinoData;
    private static ButtonGroup dataButtonGroupModalidade;
    private static ButtonGroup modalidadeButtonAdministrationGroup;
    private static ButtonGroup tornamentButtonAdministrationGroup;
    private static ButtonGroup serieButtonGroupSexo;
    private static ButtonGroup serieButtonGroupModalidade;
    private static int participanteSerieIndex;

	public Janela(){
        initQuery = new Query();
		this.setIconImage(olympic.getImage());
		this.setTitle("Sistemas de Eventos Esportivos");
		this.setSize(400,400);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		sendTornament.addActionListener(this);
		sendModalidade.addActionListener(this);
		sendPlayerModalidade.addActionListener(this);
		getPlayerTorneio.addActionListener(this);
		sendPlayer.addActionListener(this);
		sendQuery1.addActionListener(this);
		sendQuery2.addActionListener(this);
		sendQuery3.addActionListener(this);
		endCadastro.addActionListener(this);
		returnModalidade.addActionListener(this);
		endEpocaParaCadastro.addActionListener(this);
		cadastrarMarca.addActionListener(this);
		cadastrarData.addActionListener(this);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		createTabs();
	}
	private void createTabs(){
		//Criar o organizador das tabls
		//Vou receber o componente que contem o JPanel que
		//sera incorporada no JTabbedPane
		JComponent panel1 = makeAdministrationPanel("Torneios e Modalidades");
		//Adicionar o component criado acima no "gerenciador"
		//de tabs.
		JComponent panel2 = makeSeriesPanel("Series");
		JComponent panel4 = makeDataPanel("Datas");
		subPane.addTab("Modalidades", torch, panel1,"Apenas para o staff!");
		subPane.addTab("Notas",torch,panel2,"Apenas para staff");
		subPane.addTab("Datas",torch,panel4,"Apenas para staff");
		
		tabbedPane.addTab("Administração",torch,subPane,"Apenas para staff");
		//Um metodo para permitir o andar entre as tabs com
		//as arrows do teclado.
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

		//Criar Painel do Cadastro
		//JComponent panel2 = makePlayerPanel("Cadastro");
		Principal = makePlayerChoicePainel("Cadastro");
		tabbedPane.addTab("Cadastro", torch, Principal,
			                  "Se cadastre em um dos nossos Torneios!");
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		
		///Criar Painel "ToTem"
		JComponent panel3 = makeTotemPanel("Consultas");
		tabbedPane.addTab("Consultas", torch, panel3,
		                  "Realizar as Consultas do Evento");
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

		//Adicionar no Frame o tab ontroler.
		this.add(tabbedPane);
	}
	//Nesse metodo eu crio o JComponente que contem o
	//Painel que aparece quando a aba é selecioada.
	//É aqui que eu devo colocar outros componentes
	//Como botão/textfield/forms.
    @SuppressWarnings({ "unchecked", "rawtypes" })
	protected static JComponent makeSeriesPanel(String text){
        JPanel panel = new JPanel(false);        
        panel.setLayout(new GridLayout(10,2));
  
        eliminatoria = new JRadioButton("Eliminatoria");
        semifinal = new JRadioButton("Semi-finais");
        finalmatchs = new JRadioButton("Finais");
        femininoSeries = new JRadioButton("feminino");
        masculinoSeries = new JRadioButton("masculino");
        
        serieButtonGroupModalidade = new ButtonGroup();
        serieButtonGroupModalidade.add(eliminatoria);
        serieButtonGroupModalidade.add(semifinal);
        serieButtonGroupModalidade.add(finalmatchs);
        
        serieButtonGroupSexo = new ButtonGroup();
        serieButtonGroupSexo.add(femininoSeries);
        serieButtonGroupSexo.add(masculinoSeries);
        
        BoxModalidadeSerieList = new JComboBox();
        boxParticipanteSerieList = new JComboBox();
                
        femininoSeries.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                //if(BoxModalidadeSerieList.)
            	BoxModalidadeSerieList.removeAllItems();
                String [][] modalidadesStrings = initQuery.getModalidades((femininoSeries.isSelected())? femininoSeries.getText() : masculinoSeries.getText());
    	        DefaultComboBoxModel model = new DefaultComboBoxModel( modalidadesStrings[1] );
    	        BoxModalidadeSerieList.setModel( model );
            }
        });
        
        masculinoSeries.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                BoxModalidadeSerieList.removeAllItems();
    	        String [][] modalidadesStrings = initQuery.getModalidades((femininoSeries.isSelected())? femininoSeries.getText() : masculinoSeries.getText());
    	        DefaultComboBoxModel model = new DefaultComboBoxModel( modalidadesStrings[1] );
    	        BoxModalidadeSerieList.setModel( model );
            }
        });
                
        BoxModalidadeSerieList.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                System.out.println(BoxModalidadeSerieList.getSelectedItem());
                
                if(BoxModalidadeSerieList.getSelectedItem() == null)
                	return;
                
    			String modalidadeEscolhida = (String) BoxModalidadeSerieList.getSelectedItem(); 
    			String [] torneioLista = new String[1];
    			String [][] modalidadesStrings = initQuery.getModalidades((femininoSeries.isSelected())? femininoSeries.getText() : masculinoSeries.getText());
    			for(int i=0; i<modalidadesStrings.length; i++){ //Achar na matriz o nome associado a chave primaria
    				if(modalidadesStrings[1][i].equals(modalidadeEscolhida))
    				{
    					torneioLista[0] = modalidadesStrings[0][i];
    					tempSeriesModalidade = modalidadesStrings[0][i];
    					//System.out.println("Valor da "+torneioLista[0]);
    					break;
    				}
    			}
    			
    	        int serieID = 0;
				if(eliminatoria.isSelected())
    	        	serieID = 1;
    	        else if(semifinal.isSelected())
    	        	serieID = 2;
    	        else if(finalmatchs.isSelected())
    	        	serieID = 3;
    	        
                boxParticipanteSerieList.removeAllItems();
				participanteStrings = initQuery.getParticipante(tempSeriesModalidade,serieID);
    	        DefaultComboBoxModel model = new DefaultComboBoxModel( participanteStrings[0] );
    	        boxParticipanteSerieList.setModel( model );
            }
        });
        
        
        panel.add(new JLabel("Escolha o sexo da modalidade:"));
        panel.add(new JLabel(""));
        panel.add(femininoSeries);
        panel.add(masculinoSeries);
        panel.add(new JLabel("Escolha a serie:"));
        panel.add(new JLabel(""));
        panel.add(eliminatoria);
        panel.add(semifinal);
        panel.add(finalmatchs);
        panel.add(new JLabel(""));
        panel.add(new JLabel("Escolha a modalidade"));
        panel.add(BoxModalidadeSerieList);        
        panel.add(new JLabel("Escolha um Participante"));
        panel.add(boxParticipanteSerieList);
        panel.add(new JLabel("Coloque o resultado"));
        panel.add(notaSerie);
        panel.add(new JLabel(""));
        panel.add(cadastrarMarca);

        
        return panel;    	
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
	protected static JComponent makeDataPanel(String text){
        JPanel panel = new JPanel(false);        
        panel.setLayout(new GridLayout(10,2));
  
        eliminatoriaData = new JRadioButton("Eliminatoria");
        semifinalData = new JRadioButton("Semi-finais");
        finalmatchsData = new JRadioButton("Finais");
        femininoData = new JRadioButton("feminino");
        masculinoData = new JRadioButton("masculino");
        
        boxModalidadeDataList = new JComboBox();
        
        dataButtonGroupModalidade = new ButtonGroup();
        dataButtonGroupModalidade.add(eliminatoriaData);
        dataButtonGroupModalidade.add(semifinalData);
        dataButtonGroupModalidade.add(finalmatchsData);
        
        serieButtonGroupSexo = new ButtonGroup();
        serieButtonGroupSexo.add(femininoData);
        serieButtonGroupSexo.add(masculinoData);

        femininoData.addActionListener (new ActionListener () {
			public void actionPerformed(ActionEvent e) {
            	boxModalidadeDataList.removeAllItems();
                String [][] modalidadesStrings = initQuery.getModalidades((femininoData.isSelected())? femininoData.getText() : masculinoData.getText());
    	        DefaultComboBoxModel model = new DefaultComboBoxModel( modalidadesStrings[1] );
    	        boxModalidadeDataList.setModel( model );
            }
        });
        
        masculinoData.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
            	boxModalidadeDataList.removeAllItems();
                String [][] modalidadesStrings = initQuery.getModalidades((femininoData.isSelected())? femininoData.getText() : masculinoData.getText());
    	        DefaultComboBoxModel model = new DefaultComboBoxModel( modalidadesStrings[1] );
    	        boxModalidadeDataList.setModel( model );
            }
        });
        
        
        panel.add(new JLabel("Escolha o sexo da modalidade:"));
        panel.add(new JLabel(""));
        panel.add(femininoData);
        panel.add(masculinoData);
        panel.add(new JLabel("Escolha a serie:"));
        panel.add(new JLabel(""));
        panel.add(eliminatoriaData);
        panel.add(semifinalData);
        panel.add(finalmatchsData);
        panel.add(new JLabel(""));
        panel.add(new JLabel("Escolha a modalidade"));
        panel.add(boxModalidadeDataList);        
        panel.add(new JLabel("Coloque a data"));
        panel.add(dataSerie);
        panel.add(new JLabel(""));
        panel.add(cadastrarData);

        
        return panel;    	
    }
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected static JComponent makeAdministrationPanel(String text) {
    	
        JPanel panel = new JPanel(false);
        
        Feminino = new JRadioButton("feminino");
        Masculino = new JRadioButton("masculino");
        
        tornamentButtonAdministrationGroup = new ButtonGroup();
        femininoTornamentAdministracao = new JRadioButton("feminino");
        masculinoTornamentAdministracao = new JRadioButton("masculino");
        tornamentButtonAdministrationGroup.add(femininoTornamentAdministracao);
        tornamentButtonAdministrationGroup.add(masculinoTornamentAdministracao);
        
        modalidadeButtonAdministrationGroup = new ButtonGroup();
        modalidadeButtonAdministrationGroup.add(Feminino);
        modalidadeButtonAdministrationGroup.add(Masculino);
        
        BoxModalidadeAdmnistrantionList = new JComboBox();
        
        femininoTornamentAdministracao.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
            	BoxModalidadeAdmnistrantionList.removeAllItems();
                String [][] modalidadesStrings = initQuery.getModalidades((femininoTornamentAdministracao.isSelected())? femininoTornamentAdministracao.getText() : masculinoTornamentAdministracao.getText());
                DefaultComboBoxModel model = new DefaultComboBoxModel( modalidadesStrings[1] );
    	        BoxModalidadeAdmnistrantionList.setModel( model );
            }
        });
        
        masculinoTornamentAdministracao.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
            	BoxModalidadeAdmnistrantionList.removeAllItems();
                String [][] modalidadesStrings = initQuery.getModalidades((femininoTornamentAdministracao.isSelected())? femininoTornamentAdministracao.getText() : masculinoTornamentAdministracao.getText());
                DefaultComboBoxModel model = new DefaultComboBoxModel( modalidadesStrings[1] );
    	        BoxModalidadeAdmnistrantionList.setModel( model );
            }
        });


        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        
        
        panel.setLayout(new GridLayout(9,2));
        
        panel.add(new JLabel("Nome da Modalidade"));
        panel.add(v1.get(0));
        panel.add(new JLabel("Distância da Modalidade"));
        panel.add(v1.get(1));
        /**************/
        panel.add(Masculino);
        panel.add(Feminino);
        /*************/
        panel.add(sendModalidade);
        panel.add(new JLabel());
        panel.add(new JLabel("Nome do Torneio"));
        panel.add(v2.get(0));
        panel.add(new JLabel("Dificuldade do Torneio"));
        panel.add(v2.get(1));
        /**************/
        panel.add(masculinoTornamentAdministracao);
        panel.add(femininoTornamentAdministracao);
        /*************/
        panel.add(new JLabel("Escolha a Modalidade"));
        panel.add(BoxModalidadeAdmnistrantionList);
        panel.add(sendTornament);
        endEpocaParaCadastro.setBackground(Color.RED);
        panel.add(endEpocaParaCadastro);

        return panel;
    }
	 protected static JComponent makePlayerChoicePainel(String text){
	        newPlayer = new Inscricao();
		 	JPanel panel = new JPanel(false);   
		 	
	        FemininoPlayerChoice = new JRadioButton("feminino");
	        MasculinoPlayerChoice = new JRadioButton("masculino");
	        modalidadeButtonAdministrationGroup = new ButtonGroup();
	        modalidadeButtonAdministrationGroup.add(FemininoPlayerChoice);
	        modalidadeButtonAdministrationGroup.add(MasculinoPlayerChoice);
	        
	        panel.setLayout(new GridLayout(10,2));
	        v3.get(0).setText("");
	        v3.get(1).setText("");
	        v3.get(2).setText("");
	        v3.get(3).setText("");

	        panel.add(new JLabel("Nome do competidor"));
	        panel.add(v3.get(0));
	        panel.add(new JLabel("Data de Nascimento"));
	        panel.add(v3.get(1));
	        panel.add(new JLabel("Nacioalidade"));
	        panel.add(v3.get(2));
	        panel.add(new JLabel("CPF"));
	        panel.add(v3.get(3));
	        
	       // panel.add(new JLabel("Sexo"));
	        panel.add(MasculinoPlayerChoice);
	        panel.add(FemininoPlayerChoice);
	        
	        panel.add(new JLabel());
	        panel.add(sendPlayer);
	        panel.add(new JLabel());
	        panel.add(new JLabel());
	        panel.add(new JLabel());
	      
	        return panel;
	    }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected static JComponent makeModalidadeChoicePanel(String text){
		
        JPanel panel = new JPanel(false);
        initQuery = new Query();
        String [][] modalidadesStrings = initQuery.getModalidades((Feminino.isSelected())? Feminino.getText() : Masculino.getText());
        BoxModalidadeList = new JComboBox(modalidadesStrings[1]);        
    	panel.setLayout(new GridLayout(10,2));
        panel.add(new JLabel("Escolha uma das modalidades:"));
        panel.add(BoxModalidadeList);
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(sendPlayerModalidade);

        return panel;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected static JComponent makeTornamentChoicePanel(String text){
		
        JPanel panel = new JPanel(false);
        initQuery = new Query();
        importantInfoTorneio = initQuery.getTorneios(importantInfo[1]);

		BoxTornamentList = new JComboBox(importantInfoTorneio[0]);
        
    	panel.setLayout(new GridLayout(10,2));
        panel.add(new JLabel("Escolha um dos torneios:"));
        panel.add(BoxTornamentList);
        panel.add(new JLabel("Sua marca neste Torneio"));
        panel.add(v5.get(0));
        panel.add(new JLabel());
        panel.add(getPlayerTorneio);
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(returnModalidade);
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(endCadastro);
        //Create the combo box, select item at index 4.
        //Indices start at 0, so 4 specifies the pig.
        return panel;
	}
    protected static JComponent makeTotemPanel(String text){
        JPanel panel = new JPanel(false);
        panel.setLayout(new GridLayout(10,2));
        panel.add(new JLabel("Pesquisar séries do competidor:"));
        panel.add(new JLabel());
        panel.add(new JLabel("Nome do Competidor"));
        panel.add(Q1);
        panel.add(new JLabel());
        panel.add(sendQuery1);
        panel.add(new JLabel("Três melhores da prova:"));
        panel.add(new JLabel());
        panel.add(new JLabel("Nome da Prova"));
        panel.add(Q2);
        panel.add(new JLabel());
        panel.add(sendQuery2);
        panel.add(new JLabel("Participantes de cada modalide:"));
        panel.add(new JLabel());
        panel.add(new JLabel("Nome da Modalidade"));
        panel.add(Q3);
        panel.add(new JLabel());
        panel.add(sendQuery3);
        return panel;
    }

    
 	protected static Image resizeImage(String s){
    	ImageIcon icontemp = new ImageIcon(s);
		Image temp = icontemp.getImage();
		Image newimg = temp.getScaledInstance(30,30,java.awt.Image.SCALE_SMOOTH);
		return newimg;
    }
 	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == sendModalidade){
			initQuery = new Query(v1);
			if(Feminino.isSelected()){
				initQuery.sendModalidade(Feminino.getText());
			}
			else if(Masculino.isSelected()){
				initQuery.sendModalidade(Masculino.getText());		
			}

	        /*String [][] modalidadesStrings = initQuery.getModalidades((Feminino.isSelected())? Feminino.getText() : Masculino.getText());
	        BoxModalidadeAdmnistrantionList.removeAllItems();
	        DefaultComboBoxModel model = new DefaultComboBoxModel( modalidadesStrings[1] );
	        BoxModalidadeAdmnistrantionList.setModel( model );*/
	        
		}
		else if(e.getSource() == sendTornament){ //Cadastrar o torneio no banco
			String tornamentModalidade = (String) BoxModalidadeAdmnistrantionList.getSelectedItem(); 
			String [][] modalidadesStrings = initQuery.getModalidades((Feminino.isSelected())? Feminino.getText() : Masculino.getText());
			for(int i=0; i<modalidadesStrings.length; i++){
				if(modalidadesStrings[1][i].equals(tornamentModalidade)){
					tornamentModalidade = modalidadesStrings[0][i];
					break;
				}
			}
			initQuery = new Query(v2);
			initQuery.sendTornament(tornamentModalidade);
		}
		else if(e.getSource() == sendPlayer){	//Cadastrar o Participante no banco
			initQuery = new Query(v3);			

		    initQuery.sendPlayer((FemininoPlayerChoice.isSelected())? FemininoPlayerChoice.getText() : MasculinoPlayerChoice.getText());
			importantInfo[0] = v3.get(3).getText(); 	 
			newPlayer.setPlayerID(v3.get(3).getText());	//Guardar CPF
	        tabbedPane.setSelectedIndex(0);
			tabbedPane.remove(1);
			tabbedPane.insertTab("Modalidades",torch, makeModalidadeChoicePanel("Segunda Tela"),
					"Continue o cadastro", 1);
			tabbedPane.setSelectedIndex(1);
		}
		else if(e.getSource() == sendPlayerModalidade){ //Coletar a modalidade escolhida
			initQuery = new Query(v3);
			importantInfo[1] = (String) BoxModalidadeList.getSelectedItem(); 
			String temp;
			tempNomeModalidade = importantInfo[1];
			String [][] modalidadesStrings = initQuery.getModalidades((Feminino.isSelected())? Feminino.getText() : Masculino.getText());
			for(int i=0; i<modalidadesStrings.length; i++){ //Achar na matriz o nome associado a chave primaria
				if(modalidadesStrings[1][i].equals(importantInfo[1])){
					temp = importantInfo[1];
					importantInfo[1] = modalidadesStrings[0][i];
					//Adicionar no Torneio
					//Modalidade x = new Modal
					newPlayer.inserirModalidade(Double.valueOf(modalidadesStrings[0][i]), temp);
					break;
				}
			}
			
			tabbedPane.setSelectedIndex(0);
			tabbedPane.remove(1);
			tabbedPane.insertTab("Torneios",torch, makeTornamentChoicePanel("Terceira Tela"),
					"Continue o cadastro", 1);
			tabbedPane.setSelectedIndex(1);
		}
		else if(e.getSource() == returnModalidade){	//Retornar para a aba modalidade
			tabbedPane.setSelectedIndex(0);
			tabbedPane.remove(1);
			tabbedPane.insertTab("Modalidades",torch, makeModalidadeChoicePanel("Segunda Tela"),
					"Continue o cadastro", 1);
			tabbedPane.setSelectedIndex(1);
		}
		else if(e.getSource() == getPlayerTorneio){
			
			int nota;
			importantInfo[2] = (String) BoxTornamentList.getSelectedItem(); //Pegar o torneio escolhido
			if(v5.get(0).getText().equals("")){ //Marca
				nota = 0;
				v5.get(0).setText("Você colocou zero");
			}
			else
				nota = Integer.parseInt(v5.get(0).getText()); //Pegar a marca
			
			double torneioID =  0;
			int torneioDC = 0;

			for(int i=0;i<importantInfoTorneio[0].length;i++)
				if(importantInfoTorneio[0][i].equals(importantInfo[2])){	//Achei a coluna da linha 0 do nome do torneio
						torneioID = Double.valueOf(importantInfoTorneio[1][i]);
						torneioDC = Integer.parseInt(importantInfoTorneio[2][i]);
						break;
				}
			System.out.println("===========TESTE MODALIADDE=================");

			System.out.println("Nome da modalidade "+tempNomeModalidade+" ID do torneio"
					+torneioID+" Nome do Torneio "+importantInfo[2]+
						" Dificuldade do torneio "+torneioDC+" Notas"+nota);
			
			newPlayer.inserirTorneio(tempNomeModalidade,torneioID, importantInfo[2], torneioDC,nota);
		}
		else if(e.getSource() == endCadastro){
			newPlayer.makeTupla();
			initQuery = new Query();
			newPlayer.test();
			System.out.println("Size da lista de modalidade: "+newPlayer.getListadeModalidade().size());
			for(int n = 0; n< newPlayer.getListadeModalidade().size();n++){
			     initQuery.sendInscrito(Double.valueOf(newPlayer.getPlayerID()),
					newPlayer.getListadeModalidade().get(n).getModalidadeID(),
					newPlayer.getListadeModalidade().get(n).getListaTorneio().get(0).getTorneioID(),
					newPlayer.getListadeModalidade().get(n).getListaTorneio().get(0).getMarca());
			}
			tabbedPane.setSelectedIndex(0);
			tabbedPane.remove(1);
			tabbedPane.insertTab("Cadastro",torch, makePlayerChoicePainel("Primeira Tela"),
					"Se cadastre em nosso evento", 1);
			tabbedPane.setSelectedIndex(1);
		}
		else if(e.getSource() == cadastrarMarca){
			int serieID = 0,resultado = 0;
			initQuery = new Query();
	        if(eliminatoria.isSelected())
	        	serieID = 1;
	        else if(semifinal.isSelected())
	        	serieID = 2;
	        else if(finalmatchs.isSelected())
	        	serieID = 3;
	        
	        if(notaSerie.getText().equals(""))
	        	resultado = 0;
	        else
	        	resultado = Integer.parseInt(notaSerie.getText());
	        
	        participanteSerieIndex = boxParticipanteSerieList.getSelectedIndex();
	        
			initQuery.sendResultado(resultado, Double.valueOf(participanteStrings[1][participanteSerieIndex]) ,  Double.valueOf(tempSeriesModalidade), serieID);
		}
		else if(e.getSource() == cadastrarData){
			int serieID = 0;
			String dataColetada = dataSerie.getText();
			
            System.out.println(boxModalidadeDataList.getSelectedItem());
            
            if(boxModalidadeDataList.getSelectedItem() == null)
            	return;
            
			String modalidadeEscolhida = (String) boxModalidadeDataList.getSelectedItem(); 
			String [] torneioLista = new String[1];
			String [][] modalidadesStrings = initQuery.getModalidades((femininoSeries.isSelected())? femininoSeries.getText() : masculinoSeries.getText());
			for(int i=0; i<modalidadesStrings.length; i++){ //Achar na matriz o nome associado a chave primaria
				if(modalidadesStrings[1][i].equals(modalidadeEscolhida))
				{
					torneioLista[0] = modalidadesStrings[0][i];
					modalidadesData = modalidadesStrings[0][i];
					//System.out.println("Valor da "+torneioLista[0]);
					break;
				}
			}

			
			initQuery = new Query();
	        if(eliminatoriaData.isSelected())
	        	serieID = 1;
	        else if(semifinalData.isSelected())
	        	serieID = 2;
	        else if(finalmatchsData.isSelected())
	        	serieID = 3;

	        System.out.println("Data: "+ dataSerie.getText() + " SerieId " + serieID + " modalidade: " + modalidadesData );
			initQuery.sendData(Double.valueOf(modalidadesData),serieID,dataColetada );
		}
		else if(e.getSource() == sendQuery1){
			initQuery = new Query(Q1);
			initQuery.sendQuery1();
		}
		else if(e.getSource() == sendQuery2){
			initQuery = new Query(Q2);
			initQuery.sendQuery2();
		}
		else if(e.getSource() == sendQuery3){
			initQuery = new Query(Q3);
			initQuery.sendQuery3();
		}
		else if(e.getSource() == endEpocaParaCadastro)
		{
			System.out.println("Vou alocar geral");
			initQuery.aloca();
		}
		else
			System.exit(0);
	}
	
	
	
}
