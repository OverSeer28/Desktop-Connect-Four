package four;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;



    public class ConnectFour extends JFrame {
        String last_input = "";
        String columnName;
        String rowNumber;
        String firstEmptyEntry = "";

        private JPanel mainpanel;
        private JPanel panel;
        //private JPanel buttonPanel;
        TreeMap <String, String> map = new TreeMap<>();


        public ConnectFour(){
            setTitle("Connect Four");
            setSize(500, 500);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            panel = new JPanel();
            //buttonPanel = new JPanel();
            mainpanel = new JPanel();


            for(int i = 6; i > 0; i--){
                for (char ch = 'A'; ch <= 'G'; ch++) {
                    // Inside the loop, ch will take values from 'A' to 'Z'
                    String x = ch + Integer.toString(i) ;
                    map.put(x, "");
                    panel.add(new CellButton(x));
                }
            }

            // buttonPanel.add(new JButton("Reset"));

            // add(mainpanel);
            // mainpanel.add(panel);
            // mainpanel.add(buttonPanel, BorderLayout.SOUTH);
            // panel.setLayout(new GridLayout(6, 7));
            //buttonPanel.setLayout(new GridLayout(1, 2));

            mainpanel = new JPanel(new BorderLayout());
            add(mainpanel);

            panel.setLayout(new GridLayout(6, 7));
            mainpanel.add(panel, BorderLayout.CENTER);

            JButton Reset = new ResetButton("Reset");
            mainpanel.add(Reset, BorderLayout.SOUTH);

            setVisible(true);

            //panel.add(buttonPanel, BorderLayout.SOUTH);




        }




        // Method to get the panel instance
        public JPanel getPanel() {
            return panel;
        }



        void setLastInput(String x){
            this.last_input = x;
        }

        void setFirstEmptyEntry(String x){
            this.firstEmptyEntry = x;
        }

        String getFirstEmptyEntry(){
            return this.firstEmptyEntry;
        }

        String getLastInput(){
            return this.last_input;
        }


        void setColumnName (String column){
            this.columnName = column;
        }

        String getColumnName (){
            return this.columnName;
        }


        void setRowNumber (String row){
            this.rowNumber = row;
        }

        String getRowNumber (){
            return this.rowNumber;
        }




        class CellButton extends JButton  {

            public CellButton(String text) {
                super(text);
                this.setName("Button" + text);
                this.setText(" ");


                Color randomColor = new Color(156, 204, 102);
                this.setBackground(randomColor);

                setFocusPainted(false); // Remove highlighting on clicked cells





                this.addActionListener(new ActionListener() {


                    @Override
                    public void actionPerformed(ActionEvent e) {


                        //Settting the Active Column
                        for (char ch = 'A'; ch <= 'G'; ch++) {
                            // Inside the loop, ch will take values from 'A' to 'Z'
                            if (text.contains(ch+"")) {
                                System.out.println("Column " + ch + " Was clicked");
                                setColumnName(ch+"");

                            }
                        }

                        //Settting the Active Row
                        for (int i = 1; i < 7 ; i++) {
                            // Inside the loop, ch will take values from 'A' to 'Z'
                            if (text.contains(Integer.toString(i))) {
                                System.out.println("Column " + (Integer.toString(i)) + " Was clicked");
                                setRowNumber((Integer.toString(i)));

                            }
                        }



                        //Stage 1 Loop through the map and find all elements with the column in the map
                        //Stage 2 Loop through the map and find the first empty element in the column
                        //Stage 3 Pass the element as input to be amended


                        //Implementation of Stage 1
                        for (Map.Entry<String, String> entry : map.entrySet()) {
                            String key = entry.getKey();
                            String value = entry.getValue();
                            // Do something with key and value
                            // if (key.contains(columnName)) {
                            //     System.out.println("Key: " + key + ", Value: " + value);
                            // }
                        }


                        //Implementation of Stage 2

                        for (Map.Entry<String, String> entry : map.entrySet()) {
                            if (entry.getValue().isEmpty() && entry.getKey().contains(getColumnName())) {
                                setFirstEmptyEntry(entry.getKey());
                                break; // Exit loop once the first empty element is found
                            }
                        }

                        if (firstEmptyEntry != null) {
                            // System.out.println("First empty element found:");
                            // System.out.println("Key: " + firstEmptyEntry);
                        } else {
                            System.out.println("No empty elements found in the TreeMap.");
                        }




                        //Implementation of Stage 3

                        //map.put(text, "X");

                        JButton button =  findButtonByName(getPanel(), "Button"+ getFirstEmptyEntry());

                        if(button != null){
                            System.out.println(button.getName());

                            if (map.containsKey(getFirstEmptyEntry())) {

                                String value = map.get(getFirstEmptyEntry());
                                if (value.equals("X") || value.equals("O") ) {
                                    System.out.println("Already set");
                                } else {
                                    // Assuming the last input was "X", set "O" for the next input
                                    if (getLastInput().equals("X")) {
                                        button.setText("O");
                                        setLastInput("O");
                                        //System.out.println("Setting O");
                                        map.put(getFirstEmptyEntry(), "O");
                                        //Implement Checking Algo Here
                                        vertical (getPanel(), getColumnName());
                                        Horizontal(getPanel(), getRowNumber());
                                        diagonalLeft(getPanel());
                                        diagonalRight(getPanel());

                                    } else if (getLastInput().equals("O")) {
                                        // If the last input was not "X", set "X" for the next input

                                        button.setText("X");
                                        setLastInput("X");
                                        map.put(getFirstEmptyEntry(), "X");
                                        vertical (getPanel(), getColumnName());
                                        Horizontal(getPanel(), getRowNumber());
                                        diagonalLeft(getPanel());
                                        diagonalRight(getPanel());

                                    }else{
                                        button.setText("X");
                                        setLastInput("X");
                                        map.put(getFirstEmptyEntry(), "X");
                                        vertical (getPanel(), getColumnName());
                                        Horizontal(getPanel(), getRowNumber());
                                        diagonalLeft(getPanel());
                                        diagonalRight(getPanel());
                                        //System.out.println("Intial Setting X");
                                        //System.out.println(getLastInput());
                                    }
                                }
                            } else {
                                // Handle the case where the key "text" is not in the map
                                System.out.println("Key not found in the map");
                            }
                        }

                    }


                });
            }

        }


        class ResetButton extends JButton {

            public ResetButton(String text) {
                super(text);
                this.setName("ButtonReset");
                this.setText("Reset");
                this.addActionListener(new ActionListener() {
                                           @Override
                                           public void actionPerformed(ActionEvent e) {
                                               System.out.println("Reset Clicked");
                                               resetFunction(getPanel());
                                               stopGameFunction(getPanel(), true);
                                               setFirstEmptyEntry("");
                                               setLastInput("");
                                               for(Map.Entry<String, String> entry : map.entrySet()){
                                                   entry.setValue("");
                                               }


                                           }

                                       }
                );
            }
        }

        public TreeMap<String, String>  select( TreeMap<String, String> map, String text){

            //String column = getColumnName();

            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                // Do something with key and value
                if (key.contains(columnName)) {
                    System.out.println("Key: " + key + ", Value: " + value);
                }
            }




            return null;
        }


        public static JButton findButtonByName(Container container, String buttonName) {
            for (Component component : container.getComponents()) {
                if (component instanceof JButton) {
                    JButton button = (JButton) component;
                    if (button.getName() != null && button.getName().equals(buttonName)) {
                        return button;
                    }
                }
            }
            return null; // Button with the given name not found
        }


        public static void resetFunction(Container container) {

            for (Component component : container.getComponents()) {
                if (component instanceof JButton) {
                    JButton button = (JButton) component;
                    button.setText(" ");
                    Color randomColor = new Color(156, 204, 102);
                    button.setBackground(randomColor);


                    // Set button text to ""
                }
            }
        }


        public static void stopGameFunction(Container container, boolean state) {

            for (Component component : container.getComponents()) {
                if (component instanceof JButton) {
                    JButton button = (JButton) component;
                    button.setEnabled(state);

                    // Set button text to ""
                }
            }
        }
        //return null; // Button with the given name not found

        //Implement a Vetical function to check if game is won
        //Get the last Element Entered
        //Get the column where the action took place
        //Implement a count that keep track when the Button Value equals last element value
        //add a decrement to the count to keep the cout from 4 if any element other than the last element Entered is found
        public void vertical (Container container, String columnname){
            int count = 0;
            ArrayList<JButton> array = new ArrayList<>();
            for (Component component : container.getComponents()) {
                if (component instanceof JButton) {
                    JButton button = (JButton) component;
                    if (!button.getText().isEmpty() && button.getName().contains(columnname)) {
                        System.out.println(button.getName());
                        if (button.getText().equals(getLastInput())) {
                            count++;
                            array.add(button);
                            if (count == 4) {
                                System.out.println("You Won "+ getLastInput());
                                System.out.println(count);
                                for (JButton x : array) {
                                    Color randomColor = new Color(0, 255, 0);
                                    x.setBackground(randomColor);
                                }
                                stopGameFunction(getPanel(), false);
                            }
                        }else{
                            count = 0;
                            array.clear();
                        }

                    }
                }
            }

        }


        //Horizontal
        //Get the last Element Entered
        //Get the column where the action took place
        //Implement a count that keep track when the Button Value equals last element value
        //add a decrement to the count to keep the cout from 4 if any element other than the last element Entered is found
        public void Horizontal (Container container, String columnname){
            int count = 0;
            ArrayList<JButton> array = new ArrayList<>();
            for (Component component : container.getComponents()) {
                if (component instanceof JButton) {
                    JButton button = (JButton) component;
                    if (!button.getText().isEmpty() && button.getName().contains(columnname)) {
                        System.out.println(button.getName());
                        if (button.getText().equals(getLastInput())) {
                            count++;
                            array.add(button);
                            if (count == 4) {
                                System.out.println("You Won "+ getLastInput());
                                System.out.println(count);
                                for (JButton x : array) {
                                    Color randomColor = new Color(0, 255, 0);
                                    x.setBackground(randomColor);
                                }
                                stopGameFunction(getPanel(), false);
                            }
                        }else{
                            count = 0;
                            array.clear();
                        }

                    }
                }
            }

        }














        public void diagonalLeft(Container container) {
            // Iterate through each cell in the grid
            for (int row = 1; row <= 6; row++) {
                for (int col = 1; col <= 7; col++) {
                    // Convert column index to column name
                    char columnName = (char) ('A' + col - 1);

                    // Find the top-left corner of the diagonal line
                    int topLeftRow = row;
                    int topLeftCol = col;
                    while (topLeftRow > 1 && topLeftCol > 1) {
                        topLeftRow--;
                        topLeftCol--;
                    }

                    // Find the bottom-right corner of the diagonal line
                    int bottomRightRow = row;
                    int bottomRightCol = col;
                    while (bottomRightRow < 6 && bottomRightCol < 7) {
                        bottomRightRow++;
                        bottomRightCol++;
                    }

                    // Check the diagonal line from top-left to bottom-right
                    checkLeftDiagonalLine(container, topLeftRow, topLeftCol, bottomRightRow, bottomRightCol, "top-left to bottom-right");

                }

            }
        }


        public void diagonalRight(Container container) {
            for (int row = 1; row <= 6; row++) {
                for (int col = 1; col <= 7; col++) {
                    // Convert column index to column name
                    char columnName = (char) ('A' + col - 1);



                    // Find the top-right corner of the diagonal line
                    int topRightRow = row;
                    int topRightCol = col;
                    while (topRightRow > 1 && topRightCol < 7) {
                        topRightRow--;
                        topRightCol++;
                    }

                    // Find the bottom-left corner of the diagonal line
                    int bottomLeftRow = row;
                    int bottomLeftCol = col;
                    while (bottomLeftRow < 6 && bottomLeftCol > 1) {
                        bottomLeftRow++;
                        bottomLeftCol--;
                    }

                    // Check the diagonal line from top-right to bottom-left
                    checkRightDiagonalLine(container, topRightRow, topRightCol, bottomLeftRow, bottomLeftCol, "top-right to bottom-left");
                }
            }


        }


        // Method to check a diagonal line from (startRow, startCol) to (endRow, endCol)
        private void checkRightDiagonalLine(Container container, int startRow, int startCol, int endRow, int endCol, String direction) {
            int count = 0;
            ArrayList<JButton> array = new ArrayList<>();

            // Check the diagonal line
            for (int row = startRow, col = startCol; row <= endRow && col >= endCol; row++, col--) {
                JButton button = (JButton) container.getComponent((row - 1) * 7 + col - 1);
                if (!button.getText().isEmpty() && button.getText().equals(getLastInput())) {
                    count++;
                    array.add(button);
                    if (count == 4) {
                        System.out.println("You Won " + getLastInput() + " (Diagonal " + direction + ")");
                        for (JButton x : array) {
                            Color randomColor = new Color(0, 255, 0);
                            x.setBackground(randomColor);
                        }
                        stopGameFunction(getPanel(), false);
                    }
                } else {
                    count = 0;
                    array.clear();
                }
            }
        }

        private void checkLeftDiagonalLine(Container container, int startRow, int startCol, int endRow, int endCol, String direction) {
            int count = 0;
            ArrayList<JButton> array = new ArrayList<>();

            // Check the diagonal line
            for (int row = startRow, col = startCol; row <= endRow && col <= endCol; row++, col++) {
                JButton button = (JButton) container.getComponent((row - 1) * 7 + col - 1);
                if (!button.getText().isEmpty() && button.getText().equals(getLastInput())) {
                    count++;
                    array.add(button);
                    if (count == 4) {
                        System.out.println("You Won " + getLastInput() + " (Diagonal " + direction + ")");
                        for (JButton x : array) {
                            Color randomColor = new Color(0, 255, 0);
                            x.setBackground(randomColor);
                        }
                        stopGameFunction(getPanel(), false);
                    }
                } else {
                    count = 0;
                    array.clear();
                }
            }
        }


    }











