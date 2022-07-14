/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package script;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PageFormat;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Alison
 */
public class codigos {
    
    //-----------------------Testes----------------------\\
    public static void main(String[] args) {
        //esse arquivo aqui
        codigos cod = new codigos();
        
        //codigos aqui
        
        
    }

    //-----------------------Datas-----------------------\\
    
    //converte data para string
    public String converteDateString(Date dataDesejada) throws ParseException{
        //data do MySQL
        SimpleDateFormat formatoSQL = new SimpleDateFormat("yyyy-MM-dd");
        //data do Java
        SimpleDateFormat formatoJAVA = new SimpleDateFormat("dd/MM/yyyy");
        //variavel para ficar com resultado
        String result = "00/00/0000";
        //define como 00 pra n fiacar null caso a data seja null
        
        if(dataDesejada != null)
        //caso seja null ele nao tenta converter
        result = formatoJAVA.format(formatoSQL.parse(dataDesejada.toString()));
        //converte a data para string -> formato do mySQl -> formato java
        return result;
    }
    
    //converte txt para dataSQL
    public java.sql.Date converteSqlDateString(String textoDesejado){
        //formato em java
        DateFormat formatoJAVA = new SimpleDateFormat("dd/MM/yyyy");
        java.sql.Date sqlData = null; //data sql
        java.util.Date javaData; //data java

        try {
            //define o texto (##/##/####) para o formato data (yy/mm/yyyy)
            javaData = (java.util.Date) formatoJAVA.parse(textoDesejado);
            //converte a data em java para SQL (para salvar no banco de dados)
            sqlData = new java.sql.Date(javaData.getTime());
            
            //o que muda é apenas o formato da data
        
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null,"Data Invalida!", "Erro",  
                    JOptionPane.ERROR_MESSAGE);
        }
        return sqlData;
    }
    
    //define o formato data no txt formatado
    private void formataDateTXT(JFormattedTextField txtDesejado){
        try {
            //formato da data (dd/mm/yyyy)
            MaskFormatter formatoData = new MaskFormatter("##/##/####");
            formatoData.install(txtDesejado);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null,"Erro nos formatos","Erro", 
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //retorna a data atual no formato mySQL dd/mm/yy
    static java.sql.Date DataAtual() {//nao sei oq fiz aqui mas funciona
        java.util.Date date = new Date();
        date.setTime(date.getTime());
        return new java.sql.Date(date.getTime());
    }
    
    //----------------------$ Real-----------------------\\
    
    //converte float (real) para String
    public String converteFloatReal(float valorFloat){
        
        //formato com duas casas decimais apos a virgula
        DecimalFormat formatoReal = new DecimalFormat("#.00");
        //arredonda o valor pra cima 
        //(talvez de algum erro com dizimas, não testei)
        formatoReal.setRoundingMode(RoundingMode.UP);
        
        //converte para String
        String valorString = (formatoReal.format(valorFloat));
        return valorString;//ele retorna com virgula
    }
    
    //converte string (real) para float 
    public float converteRealFloat(String valorString){
        
        //troca a virgula por ponto 
        valorString = valorString.replace(",", ".");
        //converte para float
        float valorFloat = Float.parseFloat(valorString);
        return valorFloat;
    }
    
    //----------------------listas----------------------\\
    
    //filtrar objetos com Collections.sort(obj)
    //*@Override
    public int compareTo(Object obj) {
        //compara algo e retorna -1 e 0
        //se for 0 ele manda pra direita e -1 esquerda
        //obj.compareTo(algo) já retorna 0 e -1
        
        /*if(this.obj.algo > obj.algo){
            return -1;
        }else{
            return 0;
        }*/
        
        if(1 < 2){
            return 0;
        }else{
            return -1;
        }        
    }
    
    //como preenche cmb e tabela
    public void preencheLista(
        List<String> recebeLista, JTable tabelaDesejada, JComboBox cmbDesejado){

        //criar lista
        List<String> nomeLista = (List<String>) recebeLista;
        
        //tabela
        DefaultTableModel tbm = (DefaultTableModel) tabelaDesejada.getModel();
        
        //pra cada item da lista
        for(String cont : nomeLista){
            //adiciona uma linha na tabela
            tbm.addRow(//adiciona na tabela
                new Object[]{
                    //dado1,
                    //dado2
                }
            );
        }
        
        //remover itens da tabela
        int qtd = tabelaDesejada.getModel().getRowCount();
        while(qtd>0){
            tbm.removeRow(0);        
            qtd--;  
        }
        
        //comboBox
        DefaultComboBoxModel cmb = (DefaultComboBoxModel) cmbDesejado.getModel();
        
        //pra cada item da lista
        for(String cont : nomeLista){
            cmb.addElement(cont);//adiciona na cmb
        }
        
        //remover itens da cmb
        cmbDesejado.removeAllItems(); 
    }
    
    //------------------------tabela------------------------\\
    
    //muda a cor das linhas e colunas de uma tabela
    public void coresTabela(JTable tabelaDesejada){//Esse codigo eu peguei da internet
        tabelaDesejada.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            
            //não sei 100% como funciona isso ainda
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column){
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value,
                        isSelected, hasFocus, row, column);//objeto tabela
                
                //-------------Codigo da cor da linha da tabela--------------//
                
                
                
                //-----------------------------------------------------------//        
                
                return label;//nao sei pq tqm q retornar isso
            }        
        });  
    }
    
    //ação quando clica no cabeçalho da tabela
    class ColumnHeaderListener extends MouseAdapter {
        //evento quando clica
        public void mouseClicked(MouseEvent evt) {
            //pega o cabeçalho da tabela desejada
            JTable table = ((JTableHeader)evt.getSource()).getTable();
            TableColumnModel colModel = table.getColumnModel();

            // índice da coluna cujo titulo foi clicado
            int vColIndex = colModel.getColumnIndexAtX(evt.getX());
            int mColIndex = table.convertColumnIndexToModel(vColIndex);

            if(vColIndex == -1) {
              return;
            }
            
            //codigos aqui
        
        }
    }
    
    //------------------------macetes------------------------\\
    
    /*if(evt.getButton() == 1){//verifica se foi com botao esquerdo
        }*/
    
    //definir conteudo da tabela como centralizado
    //table contents -> columns -> renderer -> codigo personalizado "centralizado" 
    DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
    public void CelulaCentralizada(){ 
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
    }
    
    //------------------------Imprimir------------------------\\
    
    //coloque esse codigo em um painel que quer imprimir
    //*@Override
    int nPaginas = 1;//numeoro de paginas pra imprimir
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) 
            throws PrinterException {
        if(pageIndex < nPaginas){//numero de paginas
            //se precisar mudar os dados coloque abaixo entre if()
            Graphics2D graph = (Graphics2D) graphics;//converte a tela pra "img"
            graph.translate(pageFormat.getImageableX(),//dimensão da tela
                    pageFormat.getImageableY());
            //*printAll(graph);//adiciona "img" no pdf
            //implements Printable na tela
            return PAGE_EXISTS;//retorna a pagina
        }
        return NO_SUCH_PAGE;//nao retorna nada
    }
    
    //em seguida use essa função pra imprimir ele
    public void imprimir(JPanel painel){
        PrinterJob job = PrinterJob.getPrinterJob();
       
        //define como imprimivel
        //não da de mudar numero de paginas (acho q precisa mudar algo pra isso)
        //*job.setPrintable(painel.getComponent(0));
        //tem q adicioar no painel no init componets
        job.setJobName("Nome do arquivo");
        
        //caixa de dialogo pra saber em qual impressora ele vai usar
        if(job.printDialog()){//
            try{
                job.print();
            }catch(PrinterException ex){
                JOptionPane.showMessageDialog
                (null, "Erro ao imprimir!", "Erro", JOptionPane.WARNING_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog
            (null, "Impressao cancelada", "Cancelado", JOptionPane.WARNING_MESSAGE);
        }
    }
}
