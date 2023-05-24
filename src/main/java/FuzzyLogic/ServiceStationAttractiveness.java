package FuzzyLogic;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.RuleBlock;

public class ServiceStationAttractiveness {

    public static double Bell(double one, double tow, double thee){

        return 1;
    }
    public static void main(String[] args) {
        // Load FCL file
        String fileName = "src/main/java/FuzzyLogic/service_station_attractiveness.fcl";
        FIS fis = FIS.load(fileName, true);

        // Check for errors
        if (fis == null) {
            System.err.println("Can't load file: '" + fileName + "'");
            return;
        }


        // Set inputs
        fis.setVariable("quality", 8.2);
        fis.setVariable("speed", 8.7);
        fis.setVariable("price", 8.9);
        fis.setVariable("service_range", 7.45);

        // Evaluate
        fis.evaluate();

        // Get output
        double attractiveness = fis.getVariable("attractiveness").getValue();

        // Print output
        System.out.println("Attractiveness: " + attractiveness);

        // Show chart
        RuleBlock ruleBlock = fis.getFunctionBlock("service_station_attractiveness").getFuzzyRuleBlock("No1");
        JFuzzyChart.get().chart(ruleBlock.getFunctionBlock());
    }

}
