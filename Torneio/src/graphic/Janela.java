package graphic;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import connection.Query;


public final class Janela extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private static ImageIcon torch = new ImageIcon(resizeImage("images/torch.png"));;
	private static ImageIcon olympic = new ImageIcon(resizeImage("images/olympic.png"));
	private static JButton sendModalidade = new JButton("Cadastrar Modalidade");
	private static JButton sendTornament = new JButton("Cadastrar Torneio");
	private static JButton sendPlayer = new JButton("Cadastrar Competidor");
	private static JButton sendPlayerTorneio = new JButton("Cadastrar sua Modalidade");
	private static JButton sendQuery1 = new JButton("Send");
	private static JButton sendQuery2 = new JButton("Send");
	private static JButton sendQuery3 = new JButton("Send");
	private static JButton endCadastro = new JButton("End Phase");
	private static JButton sendPlayerModalidade = new JButton("Escolher Modalidade");
	private static JComponent Principal;
	private static JTabbedPane tabbedPane = new JTabbedPane();
	private static Vector<JTextField> v1 = new TextFields(3).getVectorText();
	private static Vector<JTextField> v2 = new TextFields(3).getVectorText();
	private static Vector<JTextField> v3 = new TextFields(4).getVectorText();
	private static Vector<JTextField> v4 = new TextFields(4).getVectorText();
	private static Vector<JTextField> v5 = new TextFields(4).getVectorText();
	private static JTextField Q1 = new JTextField();
	private static JTextField Q2 = new JTextField();
	private static JTextField Q3 = new JTextField();
	private static String [] importantInfo = new String [3];
	private static Query initQuery;
	@SuppressWarnings("rawtypes")
	private static JComboBox BoxModalidadeList;
	@SuppressWarnings("rawtypes")
	private static JComboBox BoxTornamentList;

	public Janela(){
		this.setIconImage(olympic.getImage());
		this.setTitle("Sistemas de Eventos Esportivos");
		this.setSize(400,400);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		sendTornament.addActionListener(this);
		sendModalidade.addActionListener(this);
		sendPlayerModalidade.addActionListener(this);
		sendPlayerTorneio.addActionListener(this);
		sendPlayer.addActionListener(this);
		sendQuery1.addActionListener(this);
		sendQuery2.addActionListener(this);
		sendQuery3.addActionListener(this);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		createTabs();
	}
	private void createTabs(){
		//Criar o organizador das tabls
		//Vou receber o componente que contem o JPanel que
		//sera incorporada no JTabbedPane
		JComponent panel1 = makeAdministrationPanel("Administração");
		//Adicionar o component criado acima no "gerenciador"
		//de tabs.
		tabbedPane.addTab("Administração", torch, panel1,
		                  "Apenas para o staff!");
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
    protected static JComponent makeAdministrationPanel(String text) {
    	
        JPanel panel = new JPanel(false);
        endCadastro.setBackground(Color.red);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        
        panel.setLayout(new GridLayout(12,2));
        
        panel.add(new JLabel("Nome da Modalidade"));
        panel.add(v1.get(0));
        panel.add(new JLabel("Distância da Modalidade"));
        panel.add(v1.get(1));
        panel.add(new JLabel("Sexo da Modalidade"));
        panel.add(v1.get(2));
        panel.add(sendModalidade);
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(new JLabel("Nome do Torneio"));
        panel.add(v2.get(0));
        panel.add(new JLabel("Dificuldade do Torneio"));
        panel.add(v2.get(1));
        panel.add(new JLabel("ID da Modalidade"));
        panel.add(v2.get(2));
        panel.add(sendTornament);
        panel.add(endCadastro);
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(new JLabel());

        
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
        String [] modalidadesStrings = initQuery.getModalidades();
        BoxModalidadeList = new JComboBox(modalidadesStrings);

        
    	panel.setLayout(new GridLayout(10,2));
        panel.add(new JLabel("Escolha uma das modalidades:"));
        panel.add(BoxModalidadeList);
        panel.add(new JLabel());
        panel.add(new JLabel("Sua marca nesta modalidade"));
        panel.add(v4.get(0));
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(sendPlayerModalidade);
        //Create the combo box, select item at index 4.
        //Indices start at 0, so 4 specifies the pig.
        
       // temp = petList;
        return panel;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected static JComponent makeTornamentChoicePanel(String text){
		
        JPanel panel = new JPanel(false);
        initQuery = new Query();
        String [] torneiosStrings = initQuery.getTorneios();
        //String [] torneiosStrings = {"vaca","vadia"};
        BoxTornamentList = new JComboBox(torneiosStrings);

    	panel.setLayout(new GridLayout(10,2));
        panel.add(new JLabel("Escolha um dos torneios:"));
        panel.add(BoxTornamentList);
        panel.add(new JLabel());
        panel.add(new JLabel("Sua marca neste Torneio"));
        panel.add(v5.get(0));
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(sendPlayerTorneio);
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
			initQuery.sendModalidade();
		}
		else if(e.getSource() == sendTornament){
			//Capturar as variaveis dos TextFields
			initQuery = new Query(v2);
			initQuery.sendTornament();
		}
		else if(e.getSource() == sendPlayer){
			initQuery = new Query(v3);
			initQuery.sendPlayer();
			//tabbedPane.setComponentAt(1,makeModalidadePanel("playerTorneioDados"));
			//Remover a Tab e adicionar a tab nova no lugar correto!
			
			importantInfo[0] = v3.get(3).getText(); 	//Guardar o CPF 
	        tabbedPane.setSelectedIndex(0);
			tabbedPane.remove(1);
			tabbedPane.insertTab("Cadastro(1)",torch, makeModalidadeChoicePanel("Segunda Tela"),
					"Continue o cadastro", 1);
			tabbedPane.setSelectedIndex(1);
		}
		else if(e.getSource() == sendPlayerModalidade){
			initQuery = new Query(v3);
			initQuery.sendChoiceModalidade();
			importantInfo[1] = (String) BoxModalidadeList.getSelectedItem(); //Guardar Item Escolhido

			tabbedPane.setSelectedIndex(0);
			tabbedPane.remove(1);
			tabbedPane.insertTab("Cadastro(2)",torch, makeTornamentChoicePanel("Terceira Tela"),
					"Continue o cadastro", 1);
			tabbedPane.setSelectedIndex(1);
		}
		else if(e.getSource() == sendPlayerTorneio){
			//initQuery = new Query(v5);
			//initQuery.sendChoiceTorneio();
			importantInfo[2] = (String) BoxTornamentList.getSelectedItem();
			//initQuery.sendInscription
			System.out.println(BoxTornamentList.getSelectedItem());
		}
		else if(e.getSource() == endCadastro){
			//Tirar o botao de cadastro e Chamar as procedures de rodar o torneio
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
