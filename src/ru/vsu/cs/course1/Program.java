package ru.vsu.cs.course1;

import ru.vsu.cs.util.ArrayUtils;
import ru.vsu.cs.util.SwingUtils;

import java.io.PrintStream;
import java.util.Locale;
public class Program {
    public static class CmdParams {
        public String inputFile;
        public String outputFile;
        public boolean process;
        public boolean reverseColumns;
        public boolean error;
        public boolean help;
        public boolean window;
    }
    public static CmdParams parseArgs(String[] args) {
        CmdParams params = new CmdParams();
        if (args.length > 0) {
            if (args[0].equals("--help")) {
                params.help = true;
                return params;
            }
            if (args[0].equals("--window")) {
                params.window = true;
                return params;
            }
            if (!args[0].equals("-r") && !args[0].equals("-c")) {
                params.error = true;
                params.help = true;
                return params;
            }
            if (args[0].equals("-r")) {
                params.process = true;
            } else {
                params.reverseColumns = true;
            }
            if (args.length < 2) {
                params.help = true;
                params.error = true;
                return params;
            }

            params.inputFile = args[1];
            if (args.length > 2) {
                params.outputFile = args[2];
            }
        } else {
            params.help = true;
            params.error = true;
        }
        return params;
    }
    public static void winMain() throws Exception {
        Locale.setDefault(Locale.ROOT);
        SwingUtils.setDefaultFont("Microsoft Sans Serif", 18);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameMain().setVisible(true);
            }
        });
    }
    public static void main(String[] args) throws Exception {
        CmdParams params = parseArgs(args);
        if (params.help) {
            PrintStream out = params.error ? System.err : System.out;
            out.println("Usage:");
            out.println("  <cmd> args <input-file> (<output-file>)");
            out.println("    -r  // reverse rows");
            out.println("    -c  // reverse columns");
            out.println("  <cmd> --help");
            out.println("  <cmd> --window  // show window");
            System.exit(params.error ? 1 : 0);
        }
        if (params.window) {
            winMain();
        } else {
            int[][] arr2 = ArrayUtils.readIntArray2FromFile(params.inputFile);
            if (arr2 == null) {
                System.err.printf("Can't read array from \"%s\"%n", params.inputFile);
                System.exit(2);
            }
            if (params.process) {
                arr2 = task8.process(arr2); // ← перезаписываем arr2 результатом
            }
            PrintStream out = (params.outputFile != null) ? new PrintStream(params.outputFile) : System.out;
            out.println(ArrayUtils.toString(arr2));
            out.close();
        }
    }
}
