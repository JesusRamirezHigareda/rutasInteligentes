package Frames;

import Exceptions.RecordNotFoundException;
import Models.Unit;
import Models.Location;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class FrameLocation {
    //declaracion de controles
    private final JFrame frame = new JFrame("Unit Location");//frame principal, contendra layouts y demas controles
    private final JPanel panelAccount =  new JPanel();//panel que ira dentro del frame y contendra controles de la info de la cuenta
    private final JPanel panelTable = new JPanel(); //panel para la tabla
    private final JPanel panelButtons =  new JPanel();//panel para botones solamente
    private final JLabel labelUnit = new JLabel("Unit: ");//titulo de contenedor de Numero de cuenta
    private final JLabel labelDescription = new JLabel("Description: ");//titulo de contenedor de Nombre del dueno de la cuenta
    private final JLabel labelRoute = new JLabel("Route: ");//titulo de contenedor del balance
    public final JTextField textUnit = new JTextField(10); //input de numero de cuenta, con longitud de 10 caracteres aprox
    private final JTextField textDescription = new JTextField(20); //input para nombre del cuenta habiente, con longitud de 20 caracteres aprox
    private final JTextField textRoute = new JTextField(10); //input de balance de cuenta, con longitud de 10 caracteres aprox
    private final JButton buttonSearch = new JButton("",new ImageIcon("Images/BusSearch.png"));
    private final JButton buttonClose = new JButton("Close", new ImageIcon("Images/BusCancel.png"));//boton con texto e imagen
    String column_names[]= {"Latitude","Longitude","DateTime"};
    DefaultTableModel table_model = new DefaultTableModel(column_names, 0);
    JTable table = new JTable(table_model);
    JScrollPane tableContainer = new JScrollPane(table);
    
    //crearemos el constructor del frame
    public FrameLocation(){
        //agregamos propiedades al frame, como el tamano, la locacion , etc.
        this.frame.setSize(820,600);//definimos alto y ancho
        this.frame.setMinimumSize(new Dimension(820,600)); //dejamos claro que la medida menor sera 
        this.frame.setLocationRelativeTo(null);//centramos el frame
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//especificamos que la "X" no cierra el programa
        //creamos el contenedor que llevara el frame donde iran todos los controles
        Container c = this.frame.getContentPane(); 
        c.setLayout(new BorderLayout());//se asigna un layout al container
        this.panelAccount.setLayout(new GridBagLayout());
        this.panelButtons.setLayout(new FlowLayout(FlowLayout.RIGHT));
        //acomodando el panel de 'Unit' 
        GridBagConstraints gb = new GridBagConstraints();
        gb.insets = new Insets(10,10,0,10);
        gb.weightx = .5;
        gb.anchor = GridBagConstraints.WEST;
        //asignando controles con posicione x y Y
        gb.gridy = 0; gb.gridx = 0; this.panelAccount.add(this.labelUnit, gb);
        gb.gridy = 0; gb.gridx = 1; this.panelAccount.add(this.textUnit, gb);
        this.textUnit.setEditable(false);
        this.buttonSearch.setEnabled(false);
        gb.gridy = 0; gb.gridx = 2; this.panelAccount.add(this.buttonSearch, gb);
        gb.gridy = 0; gb.gridx = 3; this.panelAccount.add(this.labelDescription, gb);
        this.textDescription.setEditable(false);//se desactiva la edicion del input textDescription
        gb.gridy = 0; gb.gridx = 4; this.panelAccount.add(this.textDescription, gb);//se asigna posicion a textDescription
        gb.gridy = 0; gb.gridx = 5; this.panelAccount.add(this.labelRoute, gb);//se asigna la posicion a label balance
        this.textRoute.setEditable(false);//se asigna no editable el input de balance
        gb.gridy = 0; gb.gridx = 6; this.panelAccount.add(this.textRoute, gb);//se asigna la posicion de input de balance
        c.add(panelAccount, BorderLayout.NORTH);//se agrega el panel account al container del frame
        //panel de table
        
        c.add(tableContainer, BorderLayout.CENTER);
       
        
        
        //panel de botones
        this.buttonClose.setHorizontalTextPosition(SwingConstants.CENTER);
        this.buttonClose.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.panelButtons.add(this.buttonClose);
        c.add(this.panelButtons, BorderLayout.SOUTH);
        
        //event handlers
        //se asiga un listener al input de numero de cuenta el cual detecta cada que se presiona una tecla 
        this.textUnit.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        //se agrega accion al momento de dar click en el boton 'buscar'
        this.buttonSearch.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                showLocationInfo();
            }
        });
        
        //se agrega accion al momento de dar click en el boton 'cerrar'
        this.buttonClose.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                close();
            }
        });        
    }
    
    //metodos
    //metodo que muestra el frame
    public void show(){
        this.frame.setVisible(true);
    }
    
    //metodo que cierra el frame
    private void close(){
        if(JOptionPane.showConfirmDialog(this.frame, "Exit Application","Confirm",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            System.exit(0);
    }
    
    //metodo que muestra la info del cuentahabiente
    public void showLocationInfo(){
        textDescription.setText("");
        textRoute.setText("");
        try{
            Unit u = new Unit(textUnit.getText());
            textDescription.setText(u.getDescritpion());
            textRoute.setText(u.getRoute());
        }catch(RecordNotFoundException ex){
            JOptionPane.showMessageDialog(this.frame,"Unit Not Found", "Error", JOptionPane.ERROR_MESSAGE);
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(this.frame,"Unit", "Error", JOptionPane.ERROR_MESSAGE);
            textUnit.requestFocus();
            textUnit.selectAll();
        }
    }
    public void addRow(String latitude, String longitude, String date)
    {
        
        Vector<Object> data = new Vector<Object>();
        data.add(latitude);
        data.add(longitude);
        data.add(date);
        table_model.addRow(data);
        
    }
}

