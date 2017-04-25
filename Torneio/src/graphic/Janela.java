package graphic;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import connection.Connect;


public final class Janela extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private static ImageIcon torch = new ImageIcon(resizeImage("images/torch.png"));;
	private static ImageIcon olympic = new ImageIcon(resizeImage("images/olympic.png"));
	private static Connect con = new Connect();
	private static JButton sendModalidade = new JButton("Cadastrar Modalidade");
	private static JButton sendTornament = new JButton("Cadastrar Torneio");
	private static Vector<JTextField> v1 = new TextFields().getVectorText();
	
	public Janela(){
		this.setIconImage(olympic.getImage());
		this.setTitle("Sistemas de Eventos Esportivos");
		this.setSize(400,400);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		sendTornament.addActionListener(this);
		sendModalidade.addActionListener(this);
		createTabs();
	}
	private void createTabs(){
		//Criar o organizador das tabls
		JTabbedPane tabbedPane = new JTabbedPane();
		
		//Vou receber o componente que contem o JPanel que
		//sera incorporada no JTabbedPane
		JComponent panel1 = makeTornamentPanel("Administração");
		//Adicionar o component criado acima no "gerenciador"
		//de tabs.
		tabbedPane.addTab("Administração", torch, panel1,
		                  "Apenas para o staff!");
		//Um metodo para permitir o andar entre as tabs com
		//as arrows do teclado.
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

		//Criar Painel do Cadastro
		JComponent panel2 = makePlayerPanel("Cadastro");
		tabbedPane.addTab("Cadastro", torch, panel2,
		                  "Se cadastre em um dos nossos Torneios!");
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		
		//Criar Painel "ToTem"
		JComponent panel3 = makePlayerPanel("Consultas");
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
    protected static JComponent makeTornamentPanel(String text) {
    	
        JPanel panel = new JPanel(false);
        
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        
        panel.setLayout(new GridLayout(10,2));
        
        panel.add(new JLabel("Nome da Modalidade"));
        panel.add(v1.get(0));
        panel.add(new JLabel("ID da Modalidade"));
        panel.add(v1.get(1));
        panel.add(new JLabel("Distância da Modalidade"));
        panel.add(v1.get(2));
        panel.add(new JLabel("Sexo da Modalidade"));
        panel.add(v1.get(3));
        panel.add(sendModalidade);
        panel.add(new JLabel());
        panel.add(new JLabel("Nome do Torneio"));
        panel.add(v1.get(4));
        panel.add(new JLabel("ID do Torneio"));
        panel.add(v1.get(5));
        panel.add(new JLabel("Dificuldade do Torneio"));
        panel.add(v1.get(6));
        panel.add(new JLabel("ID da Modalidade"));
        panel.add(v1.get(7));
        panel.add(sendTornament);
        
        return panel;
    }
    protected static JComponent makePlayerPanel(String text) {
    	
        JPanel panel = new JPanel(false);
        JTextField torneioName = new JTextField();
        JTextField torneioID = new JTextField();
        JTextField torneioDificuldade = new JTextField();
        JTextField torneioModalidade = new JTextField();
        JButton send = new JButton("Send");
        
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        
        panel.setLayout(new GridLayout(5,1));
        send.setText("Send");
        
        panel.add(new JLabel("Nome do Torneio"));
        panel.add(torneioName);
        panel.add(new JLabel("ID do Torneio"));
        panel.add(torneioID);
        panel.add(new JLabel("Dificuldade do Torneio"));
        panel.add(torneioDificuldade);
        panel.add(new JLabel("ID da Modalidade"));
        panel.add(torneioModalidade);
        panel.add(send);
        
        return panel;
    }
    protected static Image resizeImage(String s){
    	ImageIcon icontemp = new ImageIcon(s);
		Image temp = icontemp.getImage();
		Image newimg = temp.getScaledInstance(30,30,java.awt.Image.SCALE_SMOOTH);
		return newimg;
    }
    protected static JPanel Administration(JPanel p){
    	return p;
    }
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == sendModalidade){
			//Capturar as variaveis dos TextFields
			String nome,distancia,modalidadeID,sexo;
			nome = v1.get(0).getText();
			modalidadeID = v1.get(1).getText();
			distancia = v1.get(2).getText();
			sexo = v1.get(3).getText();
			System.out.println("Teste Saida");
			System.out.print(nome+"\n"+distancia+"\n"+modalidadeID+"\n"+sexo);
			//Agora vamos mandar um query pro servidor
			PreparedStatement statement = con.getVetordeStatement().get(0);
				try {
					statement.setInt(1,0);
					statement.setString(2,nome);
					statement.setInt(3,Integer.parseInt(distancia));
					statement.setString(4,sexo);
					statement.setQueryTimeout(30);
					statement.executeUpdate();
					System.out.println("Insert feito com sucesso.");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("Erro na criação do Torneio.");
				}
		}
		else if(e.getSource() == sendTornament){
			//Capturar as variaveis dos TextFields
			String nome,torneioID,modalidadeID,dificuldade;
			nome = v1.get(4).getText();
			torneioID = v1.get(5).getText();
			dificuldade = v1.get(6).getText();
			modalidadeID = v1.get(7).getText();
			System.out.println("Teste Saida");
			System.out.print(nome+"\n"+torneioID+"\n"+modalidadeID+"\n"+dificuldade);
			//Agora vamos mandar um query pro servidor
			PreparedStatement statement = con.getVetordeStatement().get(1);
				try {
					statement.setInt(1,Integer.parseInt(torneioID));
					statement.setInt(2,Integer.parseInt(modalidadeID));
					statement.setString(3,nome);
					statement.setInt(4,Integer.parseInt(dificuldade));
					statement.setQueryTimeout(30);
					statement.executeUpdate();
					System.out.println("Insert feito com sucesso.");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("Erro na criação do Torneio.");
				}

		}
		else
			System.exit(0);
	}
}
