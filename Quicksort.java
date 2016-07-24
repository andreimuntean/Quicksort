import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Sorts a list of integers in ascending order.
 *
 * @author Andrei Muntean
 */
public class Quicksort
{
    private static void swap(List<Integer> list, int firstIndex, int secondIndex)
    {
        int value = list.get(firstIndex);

        list.set(firstIndex, list.get(secondIndex));
        list.set(secondIndex, value);
    }

    private static void shuffle(List<Integer> list)
    {
        Random random = new Random();

        for (int i = 1; i < list.size(); ++i)
        {
            // Swaps values with a smaller index.
            int j = random.nextInt(i);

            swap(list, i, j);
        }
    }

    private static int partition(List<Integer> list, int startIndex, int endIndex)
    {
        int median = list.get(startIndex);
        int leftIndex = startIndex + 1;
        int rightIndex = endIndex;

        while (true)
        {
            while (leftIndex < endIndex && list.get(leftIndex) < median)
            {
                ++leftIndex;
            }

            while (rightIndex > startIndex && list.get(rightIndex) >= median)
            {
                --rightIndex;
            }

            if (leftIndex >= rightIndex)
            {
                break;
            }

            swap(list, leftIndex++, rightIndex--);
        }

        // Puts the median value (from startIndex) in the correct position.
        swap(list, startIndex, rightIndex);

        // Returns the median index.
        return rightIndex;
    }

    // Both indexes are inclusive.
    private static void sort(List<Integer> list, int startIndex, int endIndex)
    {
        if (startIndex >= endIndex)
        {
            return;
        }

        int medianIndex = partition(list, startIndex, endIndex);

        sort(list, startIndex, medianIndex - 1);
        sort(list, medianIndex + 1, endIndex);
    }

    /**
     * Sorts a list of integers in ascending order.
     *
     * @param list A list of integers.
     */
    public static void sort(List<Integer> list)
    {
        shuffle(list);
        sort(list, 0, list.size() - 1);
    }

    public static void main(String[] args) throws Exception
    {
        if (args.length == 1)
        {
            List<Integer> list = new ArrayList<>();
            Scanner scanner = new Scanner(new File(args[0]));

            while (scanner.hasNextInt())
            {
                list.add(scanner.nextInt());
            }

            sort(list);

            for (int value : list)
            {
                System.out.printf("%s ", value);
            }

            System.out.println();
        }
        else
        {
            throw new Exception("No file path was specified.");
        }
    }
}
