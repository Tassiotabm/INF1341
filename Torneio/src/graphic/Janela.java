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
	private static JTextField Q1 = new JTextField();
	private static JTextField Q2 = new JTextField();
	private static JTextField Q3 = new JTextField();
	private static String [] importantInfo = new String [3];
	private static String [][] importantInfoTorneio;
	private static String tempNomeModalidade = null;
	private static int tempDificuldadeModalidade;
	private static Query initQuery;
	private static Inscricao newPlayer = new Inscricao();
	@SuppressWarnings("rawtypes")
	private static JComboBox BoxModalidadeList;
	@SuppressWarnings("rawtypes")
	private static JComboBox BoxTornamentList;
	@SuppressWarnings("rawtypes")
	private static JComboBox BoxModalidadeAdmnistrantionList;
    private static JRadioButton Feminino;
    private static JRadioButton Masculino;
    private static ButtonGroup modalidadeButtonGroup;

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
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		createTabs();
	}
	private void createTabs(){
		//Criar o organizador das tabls
		//Vou receber o componente que contem o JPanel que
		//sera incorporada no JTabbedPane
		JComponent panel1 = makeAdministrationPanel("Toreios e Modalidades");
		//Adicionar o component criado acima no "gerenciador"
		//de tabs.
		JComponent panel2 = makeSeriesPanel("Series");
		subPane.addTab("Administração", torch, panel1,
		                  "Apenas para o staff!");
		subPane.addTab("Notas das Series",torch,panel2,"Apenas para staff");
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
		
		//Criar Painel da Modalidade depois do cadastro
		//JComponent panel4 = makeModalidadePanel("Modalidade");
		/*tabbedPane.addTab("Modalidade", torch, panel4,
			                  "Realizar as Consultas do Evento");
		*/
		
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
    protected static JComponent makeSeriesPanel(String text){
        JPanel panel = new JPanel(false);        
        panel.setLayout(new GridLayout(6,2));
        panel.add(new JButton("Teste0"));
        panel.add(new JButton("Teste1"));
        panel.add(new JButton("Teste2"));
        panel.add(new JButton("Teste3"));
        panel.add(new JButton("Teste4"));
        panel.add(new JLabel("Teste5"));
        panel.add(new JButton("Teste1"));
        panel.add(new JButton("Teste2"));
        panel.add(new JLabel("Teste3"));
        panel.add(new JButton("Teste4"));

        return panel;    	
    }
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected static JComponent makeAdministrationPanel(String text) {
    	
        JPanel panel = new JPanel(false);
        Feminino = new JRadioButton("Feminino");
        Masculino = new JRadioButton("Masculino");
        modalidadeButtonGroup = new ButtonGroup();
        modalidadeButtonGroup.add(Feminino);
        modalidadeButtonGroup.add(Masculino);

        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        
        String [][] modalidadesStrings = initQuery.getModalidades();
        BoxModalidadeAdmnistrantionList = new JComboBox(modalidadesStrings[1]);
        
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
        panel.add(new JLabel("Escolha a Modalidade"));
        panel.add(BoxModalidadeAdmnistrantionList);
        panel.add(sendTornament);
        endEpocaParaCadastro.setBackground(Color.RED);
        panel.add(endEpocaParaCadastro);

        return panel;
    }
	 protected static JComponent makePlayerChoicePainel(String text){
	        JPanel panel = new JPanel(false);        
	        panel.setLayout(new GridLayout(10,2));
	        panel.add(new JLabel("Nome do competidor"));
	        panel.add(v3.get(0));
	        panel.add(new JLabel("Data de Nascimento"));
	        panel.add(v3.get(1));
	        panel.add(new JLabel("Nacioalidade"));
	        panel.add(v3.get(2));
	        panel.add(new JLabel("CPF"));
	        panel.add(v3.get(3));
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
        String [][] modalidadesStrings = initQuery.getModalidades();
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
 	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == sendModalidade){
			initQuery = new Query(v1);
			if(Feminino.isSelected())
				initQuery.sendModalidade(Feminino.getText());
			else if(Masculino.isSelected())
				initQuery.sendModalidade(Masculino.getText());			

	        String [][] modalidadesStrings = initQuery.getModalidades();
	        BoxModalidadeAdmnistrantionList.removeAll();
	        DefaultComboBoxModel model = new DefaultComboBoxModel( modalidadesStrings[1] );
	        BoxModalidadeAdmnistrantionList.setModel( model );
	        
		}
		else if(e.getSource() == sendTornament){ //Cadastrar o torneio no banco
			String tornamentModalidade = (String) BoxModalidadeAdmnistrantionList.getSelectedItem(); 
			String [][] modalidadesStrings = initQuery.getModalidades();
			for(int i=0; i<modalidadesStrings.length; i++){
				if(modalidadesStrings[1][i].equals(tornamentModalidade)){
					tornamentModalidade = modalidadesStrings[0][i];
					tempDificuldadeModalidade = Integer.parseInt(modalidadesStrings[0][i]);
					break;
				}
			}
			initQuery = new Query(v2);
			initQuery.sendTornament(tornamentModalidade);
		}
		else if(e.getSource() == sendPlayer){	//Cadastrar o Participante no banco
			initQuery = new Query(v3);
			initQuery.sendPlayer();
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
			String [][] modalidadesStrings = initQuery.getModalidades();
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
			if(v5.get(0).getText().equals("")){
				nota = 0;
				v5.get(0).setText("Você colocou zero");
			}
			else
				nota = Integer.parseInt(v5.get(0).getText()); //Pegar a marca
			newPlayer.inserirTorneio(tempNomeModalidade,Double.valueOf(importantInfo[1]), importantInfo[2], tempDificuldadeModalidade,nota);
			//importantInfoTorneio
			newPlayer.test();
			//initQuery.sendInscription
		}
		else if(e.getSource() == endCadastro){
			newPlayer.makeTupla();
			initQuery = new Query();
			newPlayer.test();
			initQuery.sendInscrito(Double.valueOf(newPlayer.getPlayerID()),
					newPlayer.getListadeModalidade().get(0).getModalidadeID(),
					newPlayer.getListadeModalidade().get(0).getListaTorneio().get(0).getTorneioID(),
					newPlayer.getListadeModalidade().get(0).getListaTorneio().get(0).getMarca());
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
		else
			System.exit(0);
	}
}
