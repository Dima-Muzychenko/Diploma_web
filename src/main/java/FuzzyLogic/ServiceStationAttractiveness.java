package FuzzyLogic;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.RuleBlock;

import java.io.FileNotFoundException;

public class ServiceStationAttractiveness {

    public static void main(String[] args) throws FileNotFoundException {
        ServiceStationAttractiveness attractiveness = new ServiceStationAttractiveness();
        double attract = attractiveness.CountEvaluation(7.56, 8.45, 6.35, 9.545);
    }
        // Load FCL file

    public double CountEvaluation(double quality, double speed, double price, double service_range) throws FileNotFoundException {
        // Load FCL file
        String fileName = "C:\\Users\\robin\\OneDrive\\Рабочий стол\\University\\диплом\\Мій диплом\\service_station_attractiveness.fcl";
        FIS fis = FIS.load(fileName, true);

        // Check for errors
        if (fis == null) {
            System.err.println("Can't load file: '" + fileName + "'");
            throw new FileNotFoundException(fileName);
        }

        // Set inputs
        fis.setVariable("quality", quality);
        fis.setVariable("speed", speed);
        fis.setVariable("price", price);
        fis.setVariable("service_range", service_range);

        // Evaluate
        fis.evaluate();

        // Get output
        double attractiveness = fis.getVariable("attractiveness").getValue();

        // Print output
        System.out.println("Attractiveness: " + attractiveness);
        // Show chart
//        RuleBlock ruleBlock = fis.getFunctionBlock("service_station_attractiveness").getFuzzyRuleBlock("No1");
//        JFuzzyChart.get().chart(ruleBlock.getFunctionBlock());

        return attractiveness;
    }


}




