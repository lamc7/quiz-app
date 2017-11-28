package com.example.henrik.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;


public class QuestionActivity extends AppCompatActivity implements View.OnClickListener{
    Button buttonA,buttonB,buttonC,buttonD,buttonE;
    TextView textQuestion,textProgress,textTopic;
    List<Question> questions;
    Question currentQuestion;
    int correctNum = 0;
    int progressNum = 0;
    int[] usedQ = {0,0,0,0,0};
    static String topic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        topic = getIntent().getStringExtra("topic");
        buttonA = findViewById(R.id.button_A);
        buttonA.setOnClickListener(this);
        buttonB = findViewById(R.id.button_B);
        buttonB.setOnClickListener(this);
        buttonC = findViewById(R.id.button_C);
        buttonC.setOnClickListener(this);
        buttonD = findViewById(R.id.button_D);
        buttonD.setOnClickListener(this);
        buttonE = findViewById(R.id.button_E);
        buttonE.setOnClickListener(this);
        textProgress = findViewById(R.id.text_progress);
        textQuestion = findViewById(R.id.text_question);
        textTopic = findViewById(R.id.text_topic);
        AppDatabase db = AppDatabase.getAppDatabase(this);
        populateWithTestData(db);
        questions = db.questionDao().getAll();
        textQuestion.setMovementMethod(new ScrollingMovementMethod());
        update();
    }
    @Override
    public void onClick(View view){
        if (view.getId() == R.id.button_A && currentQuestion.getCorrect().equals("A")){
             correctNum ++;
        }
        else if (view.getId() == R.id.button_B && currentQuestion.getCorrect().equals("B")){
            correctNum ++;
        }
        else if (view.getId() == R.id.button_C && currentQuestion.getCorrect().equals("C")){
            correctNum ++;
        }
        else if (view.getId() == R.id.button_D && currentQuestion.getCorrect().equals("D")){
            correctNum ++;
        }
        else if (view.getId() == R.id.button_E && currentQuestion.getCorrect().equals("E")){
            correctNum ++;
        }
        progressNum++;
        update();
        if (progressNum == 5){
            Intent intent = new Intent(this, EndActivity.class);
            intent.putExtra("numCorrect", correctNum);
            startActivity(intent);
        }
    }
    public void update(){
        textProgress.setText(correctNum + "/" + progressNum);
        pickQuestion();
        buttonA.setText(currentQuestion.getAnswerA());
        buttonB.setText(currentQuestion.getAnswerB());
        buttonC.setText(currentQuestion.getAnswerC());
        buttonD.setText(currentQuestion.getAnswerD());
        buttonE.setText(currentQuestion.getAnswerE());
        textQuestion.setText(currentQuestion.getQuestion());
    }
    public void pickQuestion(){
        int index = (int)(Math.random() * questions.size());
        for (int i = 0; i < usedQ.length;i++){
            if (usedQ[i] == index){
                index = (int)(Math.random() * questions.size());
                i = 0;
            }
        }
        currentQuestion = questions.get(index);
    }
    private static Question addQuestion(final AppDatabase db, Question question) {
        db.questionDao().insertAll(question);
        return question;
    }

    private static void populateWithTestData(AppDatabase db) {
        Question question1 = new Question();
        Question question2 = new Question();
        Question question3 = new Question();
        Question question4 = new Question();
        Question question5 = new Question();
        Question question6 = new Question();
        Question question7 = new Question();
        Question question8 = new Question();
        Question question9 = new Question();
        Question question10 = new Question();
        if (topic.equals("string")){
            question1.setQuestion("Given\n" +
                    " String s = \"SOLD\";\n" +
                    "which of the following calls does NOT return 1?");
            question1.setAnswerA("s.lastIndexOf(\"OLD\");");
            question1.setAnswerB("s.lastIndexOf(\"OLD\", 0);");
            question1.setAnswerC("s.lastIndexOf(\"OLD\", 1);");
            question1.setAnswerD("s.lastIndexOf(\"OLD\", 2);");
            question1.setAnswerE("s.lastIndexOf(\"OLD\", 3);");
            question1.setCorrect("B");
            question2.setQuestion("Consider the following classes:\n" +
                    "public class A\n" +
                    "{\n" +
                    " public String toString() { return \"A\"; }\n" +
                    "}\n" +
                    "public class B extends A\n" +
                    "{\n" +
                    "}\n" +
                    "public class C extends B\n" +
                    "{\n" +
                    " public String toString() { return super.toString() + \"C\"; }\n" +
                    "}\n" +
                    "What is the output from\n" +
                    " A a = new C();\n" +
                    " System.out.println(a);");
            question2.setAnswerA("A");
            question2.setAnswerB("C");
            question2.setAnswerC("AC");
            question2.setAnswerD("NoSuchMethodException");
            question2.setAnswerE("B@ddddddC, where dddddd is a sequence of hex digits");
            question2.setCorrect("C");
            question3.setQuestion("What is the output from the following code?\n" +
                    "\t\n" +
                    "String s = \"ONION\";\n" +
                    "System.out.println(\n" +
                    "s.substring(1, 5).substring(1, 4).substring(0, 3));");
            question3.setAnswerA("I");
            question3.setAnswerB("IO");
            question3.setAnswerC("ION");
            question3.setAnswerD("ONI");
            question3.setAnswerE("NION");
            question3.setCorrect("C");
            question4.setQuestion("What is the output from the following code segment?\n" +
                    "String s = \"xoxoxo\";\n" +
                    "System.out.println(s.substring(\n" +
                    "s.indexOf(s.substring(2)), s.indexOf(s.substring(3))));\n");
            question4.setAnswerA("x");
            question4.setAnswerB("o");
            question4.setAnswerC("xo");
            question4.setAnswerD("oxoxo");
            question4.setAnswerE("xoxoxo");
            question4.setCorrect("A");
            question5.setQuestion("Given\n" +
                    "public class Plus\n" +
                    "{\n" +
                    " private String chars = \"++++++++++\";\n" +
                    " public void printSome(int n)\n" +
                    " {\n" +
                    " if (n > 1)\n" +
                    " printSome(n-1);\n" +
                    " System.out.println(chars.substring(0, n));\n" +
                    " }\n" +
                    "}\n" +
                    "public class Minus extends Plus\n" +
                    "{\n" +
                    " private String chars = \"----------\";\n" +
                    " public void printSome(int n)\n" +
                    " {\n" +
                    " System.out.println(chars.substring(0, n));\n" +
                    " if (n > 1)\n" +
                    " super.printSome(n-1);\n" +
                    " }\n" +
                    "}\n" +
                    "what is the output of\n" +
                    " Minus m = new Minus();\n" +
                    " m.printSome(3);");
            question5.setAnswerA("---\n" +
                    "--\n" +
                    "-");
            question5.setAnswerB("-\n" +
                    "++\n" +
                    "---");
            question5.setAnswerC("---\n" +
                    "-\n" +
                    "++");
            question5.setAnswerD("---\n" +
                    "+\n" +
                    "--");
            question5.setAnswerE("---\n" +
                    "+\n" +
                    "++");
            question5.setCorrect("C");
            question6.setQuestion("What is the output from\n" +
                    "String s = \"La\";\n" +
                    "s.toUpperCase();\n" +
                    "System.out.println(s + s.toLowerCase());");
            question6.setAnswerA("Lala");
            question6.setAnswerB("LAla");
            question6.setAnswerC("LaLa");
            question6.setAnswerD("laLA");
            question6.setAnswerE("LA la");
            question6.setCorrect("A");
            question7.setQuestion("What is the output from the following statement?\n" +
                    "\n" +
                    "System.out.println(\"123\".substring(1) + \"123\".substring(2)\n" +
                    "+ \"123\".substring(3));");
            question7.setAnswerA("23");
            question7.setAnswerB("123");
            question7.setAnswerC("233");
            question7.setAnswerD("123233");
            question7.setAnswerE("112123");
            question7.setCorrect("C");
            question8.setQuestion("What is the output from the following code segment?\n" +
                    "String la = \"-La\", da = \"-Da\", temp;\n" +
                    "temp = la;\n" +
                    "la += da;\n" +
                    "da = temp;\n" +
                    "System.out.println(la + da);");
            question8.setAnswerA("-La-Da");
            question8.setAnswerB("-Da-La");
            question8.setAnswerC("-La-Da-La");
            question8.setAnswerD("-La-Da-La-Da");
            question8.setAnswerE("None of the above");
            question8.setCorrect("C");
            question9.setQuestion("Consider the following classes:\n" +
                    "public class Greeting\n" +
                    "{\n" +
                    " public String getMessage() { return \"How do you do, Mr. \"; }\n" +
                    " private String getName() { return \"Smith\"; }\n" +
                    " public String toString() { return getMessage() + getName(); }\n" +
                    "}\n" +
                    "public class InformalGreeting extends Greeting\n" +
                    "{\n" +
                    " public String getMessage() { return \"Hi, \"; }\n" +
                    " private String getName() { return \"Bob\"; }\n" +
                    " public String toString() { return super.toString(); }\n" +
                    "}\n" +
                    "public class VeryInformalGreeting extends InformalGreeting\n" +
                    "{\n" +
                    " public String getMessage() { return \"'sup, \"; }\n" +
                    " private String getName() { return \"dude\"; }\n" +
                    "}\n" +
                    "What does\n" +
                    " System.out.println(new VeryInformalGreeting());\n" +
                    "display?");
            question9.setAnswerA("How do you do, Mr. Smith");
            question9.setAnswerB("Hi, Bob");
            question9.setAnswerC("'sup, Bob");
            question9.setAnswerD("'sup, dude");
            question9.setAnswerE("'sup, Smith");
            question9.setCorrect("E");
            question10.setQuestion("Consider the following method mystery:\n" +
                    " public String mystery(String str1, String str2)\n" +
                    " {\n" +
                    " if (str1.length() <= 1 || str2.length() <= 1)\n" +
                    " return str2 + str1;\n" +
                    " else\n" +
                    " return mystery (str2.substring(1), str1.substring(1));\n" +
                    " }\n" +
                    "What will\n" +
                    " System.out.println(mystery(\"GOOD\", \"IDEA\"));\n" +
                    "display?");
            question10.setAnswerA("DA");
            question10.setAnswerB("AD");
            question10.setAnswerC("IGDOEOAD");
            question10.setAnswerD("GDEAIOOD");
            question10.setAnswerE("GIODOEDA");
            question10.setCorrect("A");

        }
        else if (topic.equals("array")){
            question1.setQuestion("What values are stored in the array arr after the following code is executed?\n" +
                    " int[] arr = {1, 2, 3, 4, 5};\n" +
                    " int s = 0;\n" +
                    " for (int a : arr)\n" +
                    " {\n" +
                    " s += a;\n" +
                    " a = s;\n" +
                    " }");
            question1.setAnswerA("0, 0, 0, 0, 0");
            question1.setAnswerB("1, 2, 3, 4, 5");
            question1.setAnswerC("0, 1, 3, 6, 10");
            question1.setAnswerD("1, 3, 6, 10, 15");
            question1.setAnswerE("1, 4, 10, 20, 35.");
            question1.setCorrect("B");
            question2.setQuestion("An array of size 15 holds four 1s and eleven 0s. All 1s are grouped together in one\n" +
                    "contiguous block. How many comparisons are needed in the worst case to find the\n" +
                    "exact placement of the block of 1s?");
            question2.setAnswerA("4");
            question2.setAnswerB("5");
            question2.setAnswerC("6");
            question2.setAnswerD("11");
            question2.setAnswerE("12");
            question2.setCorrect("A");
            question3.setQuestion("Consider the following method:\n" +
                    "\n" +
                    "private boolean isSomething(int[] a, int n)\n" +
                    "{\n" +
                    "return n <= 2 ||\n" +
                    "((a[n-1] + a[n-3] == 2 * a[n-2]) && isSomething(a, n-1));\n" +
                    "}\n" +
                    "For which of the following arrays isSomething(arr, arr.length) returns\n" +
                    "false?");
            question3.setAnswerA("int[] arr = {0};");
            question3.setAnswerB("int[] arr = {1, 2};");
            question3.setAnswerC("int[] arr = {0, 3, 6};");
            question3.setAnswerD("int[] arr = {1, 2, 4, 8};");
            question3.setAnswerE("int[] arr = {1, 2, 3, 4, 5};");
            question3.setCorrect("D");
            question4.setQuestion("What is the output from the following code segment?\n" +
                    "\n" +
                    "ArrayList<String> list = new ArrayList<String>();\n" +
                    "list.add(\"A\");\n" +
                    "list.add(\"B\");\n" +
                    "list.add(\"C\");\n" +
                    "list.add(\"D\");\n" +
                    "for (int i = 0; i < list.size(); i++)\n" +
                    "list.add(list.remove(i));\n" +
                    "System.out.println(list);\n");
            question4.setAnswerA("[A, B, C, D]");
            question4.setAnswerB("[B, D, C, A]");
            question4.setAnswerC("[B, C, D, A]");
            question4.setAnswerD("[D, C, B, A]");
            question4.setAnswerE("IndexOutOfBoundsException");
            question4.setCorrect("B");
            question5.setQuestion("What is the output from the following code?\n" +
                    "LinkedList<String> list = new LinkedList<String>();\n" +
                    "list.add(\"A\");\n" +
                    "list.add(\"B\");\n" +
                    "list.add(\"X\");\n" +
                    "list.add(\"D\");\n" +
                    "list.add(\"E\");\n" +
                    "list.add(\"X\");\n" +
                    "Iterator<String> it = list.listIterator(2);\n" +
                    "while (it.hasNext())\n" +
                    "if (!it.next().equals(\"X\") && !it.next().equals(\"Y\"))\n" +
                    "it.remove();\n" +
                    "System.out.println(list);");
            question5.setAnswerA("[X, X]");
            question5.setAnswerB("[X, D, E, X]");
            question5.setAnswerC("[A, B, X, X]");
            question5.setAnswerD("[A, B, X, D, X]");
            question5.setAnswerE("NoSuchElementException");
            question5.setCorrect("D");
            question6.setQuestion("What values are stored in the array nums after the following code is executed?\n" +
                    "\n" +
                    "int[] nums = {3, 0, 4, 2, 1};\n" +
                    "int len = nums.length;\n" +
                    "for (int i = 0; i < len; i++)\n" +
                    "for (int count = 1; count <= len; count++)\n" +
                    "nums[i] = nums[nums[i]];\n");
            question6.setAnswerA("0, 0, 0, 0, 0");
            question6.setAnswerB("0, 1, 2, 3, 4");
            question6.setAnswerC("0, 0, 4, 2, 0}");
            question6.setAnswerD("0, 4, 2, 1, 3");
            question6.setAnswerE("3, 0, 4, 2, 1");
            question6.setCorrect("A");
            question7.setQuestion("What is the output from the following code segment?\n" +
                    "\n" +
                    "ArrayList<Integer> numbers = new ArrayList<Integer>();\n" +
                    "numbers.add(1);\n" +
                    "numbers.add(2);\n" +
                    "numbers.add(3);\n" +
                    "numbers.add(4);\n" +
                    "numbers.set(2, numbers.remove(1));\n" +
                    "System.out.println(numbers);");
            question7.setAnswerA("[1, 3, 2]");
            question7.setAnswerB("[1, 2, 4]");
            question7.setAnswerC("[1, 3, 4]");
            question7.setAnswerD("[2, 1, 3, 4]");
            question7.setAnswerE("None of the above");
            question7.setCorrect("A");
            question8.setQuestion("What is the output from the following code?\n" +
                    "\n" +
                    "ArrayList<Integer> list = new ArrayList<Integer>();\n" +
                    "list.add(2);\n" +
                    "list.add(1);\n" +
                    "list.add(0);\n" +
                    "int n = list.size();\n" +
                    "for (int i = 0; i < n; i++)\n" +
                    "{\n" +
                    "int v = list.get(i);\n" +
                    "if (v > 0)\n" +
                    "list.add(0, v);\n" +
                    "}\n" +
                    "System.out.println(list);");
            question8.setAnswerA("[2, 1, 0, 1, 2]");
            question8.setAnswerB("[2, 1, 0, 2, 1]");
            question8.setAnswerC("[2, 1, 2, 1, 0]");
            question8.setAnswerD("[2, 2, 2, 2, 1, 0]");
            question8.setAnswerE("No output: the program goes into an “infinite” loop and eventually runs out of\n" +
                    "memory.");
            question8.setCorrect("D");
            question9.setQuestion("What is the output of the following code segment?\n" +
                    "List<String> cities = new ArrayList<String>();\n" +
                    "cities.add(\"Atlanta\");\n" +
                    "cities.add(\"Boston\");\n" +
                    "for (int i = 1; i < cities.size(); i++)\n" +
                    "cities.add(i, \"+\");\n" +
                    "System.out.println(cities);");
            question9.setAnswerA("[Atlanta, Boston]");
            question9.setAnswerB("[Atlanta, +, Boston]");
            question9.setAnswerC("[Atlanta, Boston, +]");
            question9.setAnswerD("[Atlanta, +, Boston, +]");
            question9.setAnswerE("No output because the program goes into an infinite loop");
            question9.setCorrect("E");
            question10.setQuestion("What is the value of a[1] after the following code is executed?\n" +
                    "\n" +
                    "int[] a = {0, 2, 4, 1, 3};\n" +
                    "for (int i = 0; i < a.length; i++)\n" +
                    "a[i] = a[(a[i] + 3) % a.length];");
            question10.setAnswerA("0");
            question10.setAnswerB("1");
            question10.setAnswerC("2");
            question10.setAnswerD("3");
            question10.setAnswerE("4");
            question10.setCorrect("B");
        }
        else{
            question1.setQuestion("The method fun is defined as follows:\n" +
                    "public int fun(int n)\n" +
                    "{\n" +
                    "if (n == 1)\n" +
                    "return 1;\n" +
                    "else\n" +
                    "{\n" +
                    "int m = n/2;\n" +
                    "return fun(m) + 2 * m * (n-m) + fun(n - m);\n" +
                    "}\n" +
                    "}\n" +
                    "What does fun(9) return?");
            question1.setAnswerA("0");
            question1.setAnswerB("0");
            question1.setAnswerC("18");
            question1.setAnswerD("41");
            question1.setAnswerE("81");
            question1.setCorrect("E");
            question2.setQuestion("What is the size of a double variable in Java?");
            question2.setAnswerA("2 bytes");
            question2.setAnswerB("4 bytes");
            question2.setAnswerC("8 bytes");
            question2.setAnswerD("It depends on a compiler setting");
            question2.setAnswerE("It depends on the operating system");
            question2.setCorrect("C");
            question3.setQuestion("public interface InterfaceA { void methodA(); }\n" +
                    "public interface InterfaceB extends InterfaceA { void methodB(); }\n" +
                    "public class ClassA implements InterfaceA\n" +
                    "{\n" +
                    "public void methodA() {}\n" +
                    "public void methodB() {}\n" +
                    "}\n" +
                    "public class ClassB extends ClassA implements InterfaceB\n" +
                    "{\n" +
                    "public ClassB() {}\n" +
                    "... < methods not shown >\n" +
                    "}\n" +
                    "What is the minimum set of methods that must be defined in classB for it to compile\n" +
                    "with no errors?\n");
            question3.setAnswerA("No particular methods are required");
            question3.setAnswerB("methodA");
            question3.setAnswerC("methodB");
            question3.setAnswerD("methodA and methodB");
            question3.setAnswerE("methodA, methodB, and toString");
            question3.setCorrect("A");
            question4.setQuestion("public interface InterfaceA { void methodA(); }\n" +
                    "public interface InterfaceB extends InterfaceA { void methodB(); }\n" +
                    "public class ClassA implements InterfaceA\n" +
                    "{\n" +
                    "public void methodA() {}\n" +
                    "public void methodB() {}\n" +
                    "}\n" +
                    "public class ClassB extends ClassA implements InterfaceB\n" +
                    "{\n" +
                    "public ClassB() {}\n" +
                    "... < methods not shown >\n" +
                    "}\n" +
                    "Which of the following statements causes a syntax error?\n");
            question4.setAnswerA("InterfaceA obj = new ClassA();");
            question4.setAnswerB("InterfaceB obj = new ClassA();");
            question4.setAnswerC("InterfaceA obj = new ClassB();");
            question4.setAnswerD("InterfaceB obj = new ClassB();");
            question4.setAnswerE("ClassA obj = new ClassB();");
            question4.setCorrect("B");
            question5.setQuestion("Suppose an array of n elements is sorted in ascending order. Then 5 elements are\n" +
                    "picked randomly and assigned random values. Which of the following sorting\n" +
                    "algorithms guarantees to restore the ascending order in that array using no more than\n" +
                    "O(n) comparisons?\n" +
                    " I. Selection Sort II. Insertion Sort III. Mergesort");
            question5.setAnswerA("I only");
            question5.setAnswerB("II only");
            question5.setAnswerC("I and II");
            question5.setAnswerD("II and III");
            question5.setAnswerE("I, II, and III");
            question5.setCorrect("B");
            question6.setQuestion("twist is defined as follows:\n" +
                    " public void twist(String[] w)\n" +
                    " {\n" +
                    " String temp = w[0].substring(0, 1);\n" +
                    " w[0] = w[1].substring(0, 1) + w[0].substring(1);\n" +
                    " w[1] = temp + w[1].substring(1);\n" +
                    " }\n" +
                    "What is the output of the following code segment?\n" +
                    " String[] words = {\"HOW\", \"NEAT\"};\n" +
                    " twist(words);\n" +
                    " System.out.println(words[0] + \" \" + words[1]);");
            question6.setAnswerA("NOW NOW");
            question6.setAnswerB("HOW HOW");
            question6.setAnswerC("NOW HOW");
            question6.setAnswerD("HOW NEAT");
            question6.setAnswerE("NOW HEAT");
            question6.setCorrect("E");
            question7.setQuestion("If Crossword extends Puzzle and implements Solvable, and\n" +
                    " Puzzle p = new Puzzle();\n" +
                    " Crossword cw = new Crossword(10, 20);\n" +
                    "are declared, which of the following statements will cause a syntax error?");
            question7.setAnswerA("p = cw;");
            question7.setAnswerB("cw = new Puzzle();");
            question7.setAnswerC("p = new Crossword(10, 20);");
            question7.setAnswerD("Solvable x = new Crossword(10, 20);");
            question7.setAnswerE("All of the above will compile with no errors. ");
            question7.setCorrect("B");
            question8.setQuestion("What is the output of the following code?\n" +
                    " List<Integer> nums = new ArrayList<Integer>(3);\n" +
                    " nums.add(1);\n" +
                    " nums.add(2);\n" +
                    " nums.add(0, nums.get(1));\n" +
                    " Integer x = nums.get(0);\n" +
                    " Integer y = nums.get(2);\n" +
                    " if (x == y)\n" +
                    " System.out.println(x + \" is equal to \" + y);\n" +
                    " else\n" +
                    " System.out.println(x + \" is NOT equal to \" + y);");
            question8.setAnswerA("1 is equal to 2");
            question8.setAnswerB("1 is NOT equal to 2");
            question8.setAnswerC("2 is equal to 2");
            question8.setAnswerD("2 is NOT equal to 2");
            question8.setAnswerE("IndexOutOfBoundsException");
            question8.setCorrect("C");
            question9.setQuestion("If a is an int array of length 2 and a[0] and a[1] hold values 7 and 13 respectively,\n" +
                    "what are their values after fun(a) is called? The method fun is defined as follows:\n" +
                    " public void fun(int[] x)\n" +
                    " {\n" +
                    " x[0] = (int)(100.0 * x[0] / (x[0] + x[1]));\n" +
                    " x[1] = (int)(100.0 * x[1] / (x[0] + x[1]));\n" +
                    " }");
            question9.setAnswerA("7 and 13");
            question9.setAnswerB("35 and 27");
            question9.setAnswerC("34 and 64");
            question9.setAnswerD("35 and 65");
            question9.setAnswerE("34 and 66");
            question9.setCorrect("B");
            question10.setQuestion("Suppose x is an int variable that holds a positive integer. Consider the following five\n" +
                    "expressions:");
            question10.setAnswerA("x % 100 / 10");
            question10.setAnswerB("x / 10 % 10");
            question10.setAnswerC("(x - x % 100) / 10");
            question10.setAnswerD("x / 10 - x / 100 * 10");
            question10.setAnswerE("(x - x / 100 * 100) / 10");
            question10.setCorrect("C");
        }
        addQuestion(db,question1);
        addQuestion(db,question2);
        addQuestion(db,question3);
        addQuestion(db,question4);
        addQuestion(db,question5);
        addQuestion(db,question6);
        addQuestion(db,question7);
        addQuestion(db,question8);
        addQuestion(db,question9);
        addQuestion(db,question10);
    }
}
