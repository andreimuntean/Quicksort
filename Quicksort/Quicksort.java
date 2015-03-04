import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Sorts a list in ascending order.
 *
 * @author Andrei Muntean
 */
public class Quicksort
{
    // Both indexes are inclusive.
    private static void sort(ArrayList<Comparable> list, int startIndex, int endIndex)
    {
        if (startIndex < endIndex)
        {
            int lowerIndex = startIndex;
            int medianIndex = (startIndex + endIndex) / 2;
            int upperIndex = endIndex;
            Comparable medianValue = list.get(medianIndex);

            while (lowerIndex < upperIndex)
            {
                while (list.get(lowerIndex).compareTo(medianValue) < 0)
                {
                    ++lowerIndex;
                }

                while (list.get(upperIndex).compareTo(medianValue) > 0)
                {
                    --upperIndex;
                }

                if (lowerIndex < upperIndex)
                {
                    Comparable valueAtLowerIndex = list.get(lowerIndex);

                    list.set(lowerIndex++, list.get(upperIndex));
                    list.set(upperIndex--, valueAtLowerIndex);
                }
                else if (lowerIndex == upperIndex)
                {
                    ++lowerIndex;
                    --upperIndex;
                }
            }

            sort(list, startIndex, upperIndex);
            sort(list, lowerIndex, endIndex);
        }
    }

    /**
     * Sorts a list in ascending order.
     *
     * @param list A list.
     */
    public static void sort(ArrayList<Comparable> list)
    {
        sort(list, 0, list.size() - 1);
    }

    public static void main(String[] args)
    {
        try
        {
            if (args.length == 1)
            {
                ArrayList<Comparable> list = new ArrayList<Comparable>();
                Scanner scanner = new Scanner(new File(args[0]));

                while (scanner.hasNextInt())
                {
                    list.add(scanner.nextInt());
                }

                sort(list);

                // Outputs the ordered sequence.
                // It is recommended for the user to redirect this output.
                for (Comparable value : list)
                {
                    System.out.println(value);
                }
            }
            else
            {
                throw new Exception("No file path was specified.");
            }
        }
        catch (Exception exception)
        {
            System.err.println("An error has occurred: " + exception.getMessage());
        }
    }
}