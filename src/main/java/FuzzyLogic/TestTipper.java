package FuzzyLogic;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.RuleBlock;

//import net.sourceforge.jFuzzyLogic.rule.FuzzyRuleSet;
public class TestTipper {
    public static void main(String[] args) throws Exception {
        // Load from 'FCL' file (можливо тут потрібно весь шлях вказати)
        String fileName = "src/main/java/FuzzyLogic/tipper.fcl";
        FIS fis = FIS.load(fileName,true);
        // Error while loading?
        if( fis == null ) {
            System.err.println("Can't load file: '"
                    + fileName + "'");
            return;
        }

        // Set inputs
        fis.setVariable("service", 1);
        fis.setVariable("food", 8.2);

        // Evaluate
        fis.evaluate();

        // Get output
        double tip = fis.getVariable("tip").getValue();
        // Print output
        System.out.println("tip: " + tip);

        // Show chart
        RuleBlock ruleBlock = fis.getFunctionBlock("tipper").getFuzzyRuleBlock("No1");
        JFuzzyChart.get().chart(ruleBlock.getFunctionBlock());
    }
}
