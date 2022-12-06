import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Solution {

    Queue<String> readFile(String pathname) {
        Queue<String> queue = new LinkedList<>();
        try {
            Scanner scanner = new Scanner(new File(pathname));
            while (scanner.hasNextLine())
                queue.add(scanner.nextLine());
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return queue;
    }

    public int part1(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
            if (i < 3)
                continue;
            if (map.size() == 4)
                return i + 1;
            map.put(s.charAt(i - 3), map.get(s.charAt(i - 3)) - 1);
            if (map.get(s.charAt(i - 3)) == 0)
                map.remove(s.charAt(i - 3));
        }
        return -1;
    }

    public int part2(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
            if (i < 13)
                continue;
            if (map.size() == 14)
                return i + 1;
            map.put(s.charAt(i - 13), map.get(s.charAt(i - 13)) - 1);
            if (map.get(s.charAt(i - 13)) == 0)
                map.remove(s.charAt(i - 13));
        }
        return -1;
    }

    @Test
    public void small1_1() {
        Assert.assertEquals(7, part1("mjqjpqmgbljsphdztnvjfqwrcgsmlb"));
    }

    @Test
    public void small1_2() {
        Assert.assertEquals(5, part1("bvwbjplbgvbhsrlpgdmjqwftvncz"));
    }

    @Test
    public void small1_3() {
        Assert.assertEquals(6, part1("nppdvjthqldpwncqszvftbrmjlhg"));
    }

    @Test
    public void small1_4() {
        Assert.assertEquals(10, part1("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"));
    }

    @Test
    public void small1_5() {
        Assert.assertEquals(11, part1("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"));
    }


    @Test
    public void large1() {
        Assert.assertEquals(1578, part1("mvwvrwvwbblffmvmhmllmzmqzzbhbnnvrnvnmvvqrqrccpsstvvshvvpvvpddzwwjddfzzgbbzcchrrtzzmfffzqqsjjbfbjfjwffhrhrgrfrsfrsffbwwphwhgwgmgnggzwzhwzzpggnccdbcbssgccqgcgcvgccwffrqfqtqmqppwphhlbblnnwhwfhwhmhzhmmzbmbbjjmcjcggwlgltldttpccjlccbjccvfcvvdbvdvndvvmbmcbmmhphpbhhgjgllmljlqlwlclzlccrvrdvdzvzzzbrbrnbblplrplpsstdtjddgjgjgwjwzwbzwwrhwwbzwbwpwssvgvnnwnvvjqqswwsccwctwtntdndffjzfzmfzmfzmfzzhjzhzssdsgsnggbbqdqlqbllmglgddqppjjtzttlflrrczcmzzsppvfpvffcfrccvvvhjvhvfhfbbwdwrwswqqftfbfcfdfnfpnfflbbcmcqqsqwqqccmwmvwvffvdvlvtvqttnllqslsnllzplldmmzczhzmzzhllpfpqpnnqwwwrzzdpzzhccfvcvclcnnrjrmjrjlrlflqqtpqqdgqghhwdwdttlztttwmmvfmftfptphhddswdsdpdvpvpcpjjnvjvqvhvqqqfgqqgwqgwghwwmmvmvfmvvnvsslcclzlslppztzfzbzllcgllcsllvgvqvtqtsstztwwgjgghnhnhdnnqgnntqqtccdmcmhmpphpmmmbpbptpnnnmnppwlpwwfpwfwmfwfssbspslsflljpppbsspwptpwttfbtfftltvlttdmttllmggfcgfccvwcvczvvgvsgswsrrwppgcpggpgnngfnndrrdjrdrfdfmmjmsmbmmwjjjfzfdfbdbbtgbbcsbccgbbrvvrqrwqwmmhrhhbcbdbvdvzdvdjvvnwntwwdfwwrhrmhhnghhncccfttmpmrrjhhqddrsrvvssmrrgvgdgmmfqqhttspswpwlplpjljcjmjmvmdvvhbvvtqvqccczwwtlllztlzzpnnqznzddqsqvsshrrmccnppgrrjcjpccrvcvnvcncllffvnnbsbhhrmhmzzzhrhchjccgjcjwwhddmfmhhmrrrbccqddmjdmjdjtdjjmttzstsctssvvgghgbbhmhzmmjqqqqjgjfjpffpmpzmzszfzgzcggpmpnnqjqffgpghhszzvlzzdgzggqmggqlllcwwtzwwcfwccpjpggtqgtttzffdpffjzfzddzppprnprpqqbppvjpjzppbzpbzppwzzcnndsdzszbssqwqggnhggnzggpcpnndssghsggmfmrffvddvwvssjmjrmrmnndwdwhdhwwfjfjwjmwwqssmdsmdmssqzzrgzzjttnqnfqfcccvvmsvsvffccdjdccgtgqqjwqqnbqqnhqhrqrmrgmrrzjzrznzllwrlrbbcppctptwtfttlntnltnlnzzhrzhrzrtzzvsvfvjffsbsnbbfpphtptjjmffqgqnggnqnsshffslflggtlggmpggnfgfbgbfbcbbtdbdjqwfggzsfmzflttgdfqchhtjfwlmdnsbvmqcrhsrtwtjlmnlwbvjvqdzswdthbjslqzgmtzfjfcrgbhrrjtzgljbqfrzzqszhddnmzpnbgnflghnflwdmjdhgpnvwvnltcngwggqdznrpdtwsrclwwlfzhnjtpqcgzjqjnhcwbhndwvgrczhzwfjrdvjztdbjshmhrrqctjwpcdnpnvrtggrmhzhdtlntjphcddplgfvvmjzcbbpjbqbjwmnwqgftbmchghwvrlptvnfbffvgtdbtnfzwdmdlgzjbrvffvbrfwgjzzpbpcdzhcjbhfwnmqqwvjvnpgdqdjsmwdmrgfqrwhhqqjpfmvlncfdjchrccwpbptccdchqwvbcqzlhbfgrdzgncbwrzqpfszrcwtmnvbztjlzjlrqqfrnplhnmdjljcpnqssthhmjqrrlwdvsjswdwtfstmbvwhbptjnphjmnllbwffppzmdpchbcwcmgqzfmdqvhcmrvtgtjwlzfpnjnjtfvdhtjlqsdjwrrnflnsqrtfsnbjfdllhqslltsjqzdfqthqjjgrtqjwmlqqlhvszqswmdcbnwqgshpvfjdtvsqjvcnrnvtpfsgqszhtmcdlsqjwmttcqsdlvssrgwhtmtqwmttptnmgzpwzzrbwtsrjhmjpblztgftppcwwrjppvwlvdhwwdlfcdvhwpvhqpwrqsczvlmtghrrvqbphljcmcrfmwltlmnzchzrlbgspdjwfbhrmnfhvjwlgtghcpbfgdvrmwbqbprfvwpzqzrgqbhjtztwhjcjtncmbgjphsgdfvjwjljlwzhsdldqtdvgtzpwtmrbnpvqrmfdwngzqtsdjjztslrwdnfwgbnsjblcjzvmgbqllmdvvvdcvplgbzmhcrpbbjbfzhjgfpmjtrpmgvshzvqhcbcjzfcsvqggjcllcnjlrwqfpzldswzjgzvqwvszhrntnvlcpdcfqhqrtmhbdjqpfblrsbrhdfdwfgbhsnnjpgjvfpssfmhdbdncfrqbzpbttrhfzqnrttltqbvmhglbdpwmdbmgwcdsdsflmcnphwvbbhgqpqmwbdsmqwhcdftdlcfnstlfnzzsjhqzlsdmhpvlcqvhhlpdtzlqzlvbbwhhdsqhntbtpjjvnjlrncfmvnqmzwpldgrbfcfgdtlmrfzcbqfhvhpmsrrlnqwfggwrjsmnpnqhvvcfsfspfrmhwmbpqfhprzsswrczttlczhcqvgqqsnbhhfszqbswgtcjgddhngtcvlwqqmqzntrcwgwjhmhlgclpgtmqnpgwwhnjfdvvjgmqjlzsmwztpsdzrjlshswzljlmdzfqmqbtgtzlsfwqvwrdchjvrdnwdbprfvdvczstvnzfzzfmzbwjhtflbmhlgmfzrdfrqwfbltwqlqrghlprppvlqwggvczdcnmrmblhcfmpzdmqppgwhbbrjlzsvmsfzlrdljftcrdfdgmvrccwszpjmvdnbmfdnsgmgzsdpbrlsqvsmcwwqlwtwbgfvwtsmpqlnhjchnzrpncgtcqgwqrmqmrwsbmdvqcpggpzcjgtrhvnbpprlwfnjlvrgrdjjvncjmmflrpczqnlbqczggssqfcjcrghqlsztpdjpbbfcdjzzdjbljlsczdmrgshdscvhnltrwchjcmjzmjbhldnqzwwpswsnsldcbdcdpdgpjgwrnfbcpjtvzlhvgggldcfqcwwppvltsvsmwzwdgmhnfggtgtwldmrglcvmstgmbgbjhwwhgdhfrbjlwfhjfppdmffblbbzrplhlhqlsrnsthvjtglqntzcncmvhqnlsvvrscrhlncgncjswfgcvgjlwsndzmsbhbdqsggdgrsfqtzwnjpsdlbsqjrrjwwlbptwqpfqwvpsrtrmstddzdbjjlwvfqcpfczpsnmcgbfpbwcpljdhrgsqftbnplccjphwsdbprqfqqfcvtcdznnhrbdqpccphvdgtspmzzdbnslnrvtfrtbhcfzfgmhrttmdpwftvccjbllhqgtmpgwvbdjgtvtfbfnnsfgzqjmrpbcmqhpfbstznbvgtbhnwbbnjfsthdgdrpfdtvrtmgbwzqqpnlltbshjvnhsmqhqwzgbsfqqccfznjtnnzdrgcvwnlffdgvvqzvwhwfswdmqlrsglntzsnwjzgrhhwzzshwsfmlmrbnmrqlptmjgmtqctrmddzghsgtrbbcsmhtcnrzwmvrjmrnmhbjmflrclvlbzwbgmtnmwqgfmbbnnrdvhqcflglvzbmjzjtvnmrbgghnccfrphjshsgtrhfmmghhpwgclfvzfbdccsfrlfwtsjjnhlndpwcjdtlllhcsvwrwsbppqwhfcvnsnrvthrsbgmgjhpmjdndmdqdgzfvbqfmgfjrnrjchstrjprfwfnjqblhjdgsstvtpcsvmpbhggnwzncpjdhrcllcghhprhwhfgsqpfzphrdlcbrccglsb"));
    }

    @Test
    public void small2_1() {
        Assert.assertEquals(19, part2("mjqjpqmgbljsphdztnvjfqwrcgsmlb"));
    }

    @Test
    public void small2_2() {
        Assert.assertEquals(23, part2("bvwbjplbgvbhsrlpgdmjqwftvncz"));
    }

    @Test
    public void small2_3() {
        Assert.assertEquals(23, part2("nppdvjthqldpwncqszvftbrmjlhg"));
    }

    @Test
    public void small2_4() {
        Assert.assertEquals(29, part2("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"));
    }

    @Test
    public void small2_5() {
        Assert.assertEquals(26, part2("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"));
    }

    @Test
    public void large2() {
        Assert.assertEquals(2178, part2("mvwvrwvwbblffmvmhmllmzmqzzbhbnnvrnvnmvvqrqrccpsstvvshvvpvvpddzwwjddfzzgbbzcchrrtzzmfffzqqsjjbfbjfjwffhrhrgrfrsfrsffbwwphwhgwgmgnggzwzhwzzpggnccdbcbssgccqgcgcvgccwffrqfqtqmqppwphhlbblnnwhwfhwhmhzhmmzbmbbjjmcjcggwlgltldttpccjlccbjccvfcvvdbvdvndvvmbmcbmmhphpbhhgjgllmljlqlwlclzlccrvrdvdzvzzzbrbrnbblplrplpsstdtjddgjgjgwjwzwbzwwrhwwbzwbwpwssvgvnnwnvvjqqswwsccwctwtntdndffjzfzmfzmfzmfzzhjzhzssdsgsnggbbqdqlqbllmglgddqppjjtzttlflrrczcmzzsppvfpvffcfrccvvvhjvhvfhfbbwdwrwswqqftfbfcfdfnfpnfflbbcmcqqsqwqqccmwmvwvffvdvlvtvqttnllqslsnllzplldmmzczhzmzzhllpfpqpnnqwwwrzzdpzzhccfvcvclcnnrjrmjrjlrlflqqtpqqdgqghhwdwdttlztttwmmvfmftfptphhddswdsdpdvpvpcpjjnvjvqvhvqqqfgqqgwqgwghwwmmvmvfmvvnvsslcclzlslppztzfzbzllcgllcsllvgvqvtqtsstztwwgjgghnhnhdnnqgnntqqtccdmcmhmpphpmmmbpbptpnnnmnppwlpwwfpwfwmfwfssbspslsflljpppbsspwptpwttfbtfftltvlttdmttllmggfcgfccvwcvczvvgvsgswsrrwppgcpggpgnngfnndrrdjrdrfdfmmjmsmbmmwjjjfzfdfbdbbtgbbcsbccgbbrvvrqrwqwmmhrhhbcbdbvdvzdvdjvvnwntwwdfwwrhrmhhnghhncccfttmpmrrjhhqddrsrvvssmrrgvgdgmmfqqhttspswpwlplpjljcjmjmvmdvvhbvvtqvqccczwwtlllztlzzpnnqznzddqsqvsshrrmccnppgrrjcjpccrvcvnvcncllffvnnbsbhhrmhmzzzhrhchjccgjcjwwhddmfmhhmrrrbccqddmjdmjdjtdjjmttzstsctssvvgghgbbhmhzmmjqqqqjgjfjpffpmpzmzszfzgzcggpmpnnqjqffgpghhszzvlzzdgzggqmggqlllcwwtzwwcfwccpjpggtqgtttzffdpffjzfzddzppprnprpqqbppvjpjzppbzpbzppwzzcnndsdzszbssqwqggnhggnzggpcpnndssghsggmfmrffvddvwvssjmjrmrmnndwdwhdhwwfjfjwjmwwqssmdsmdmssqzzrgzzjttnqnfqfcccvvmsvsvffccdjdccgtgqqjwqqnbqqnhqhrqrmrgmrrzjzrznzllwrlrbbcppctptwtfttlntnltnlnzzhrzhrzrtzzvsvfvjffsbsnbbfpphtptjjmffqgqnggnqnsshffslflggtlggmpggnfgfbgbfbcbbtdbdjqwfggzsfmzflttgdfqchhtjfwlmdnsbvmqcrhsrtwtjlmnlwbvjvqdzswdthbjslqzgmtzfjfcrgbhrrjtzgljbqfrzzqszhddnmzpnbgnflghnflwdmjdhgpnvwvnltcngwggqdznrpdtwsrclwwlfzhnjtpqcgzjqjnhcwbhndwvgrczhzwfjrdvjztdbjshmhrrqctjwpcdnpnvrtggrmhzhdtlntjphcddplgfvvmjzcbbpjbqbjwmnwqgftbmchghwvrlptvnfbffvgtdbtnfzwdmdlgzjbrvffvbrfwgjzzpbpcdzhcjbhfwnmqqwvjvnpgdqdjsmwdmrgfqrwhhqqjpfmvlncfdjchrccwpbptccdchqwvbcqzlhbfgrdzgncbwrzqpfszrcwtmnvbztjlzjlrqqfrnplhnmdjljcpnqssthhmjqrrlwdvsjswdwtfstmbvwhbptjnphjmnllbwffppzmdpchbcwcmgqzfmdqvhcmrvtgtjwlzfpnjnjtfvdhtjlqsdjwrrnflnsqrtfsnbjfdllhqslltsjqzdfqthqjjgrtqjwmlqqlhvszqswmdcbnwqgshpvfjdtvsqjvcnrnvtpfsgqszhtmcdlsqjwmttcqsdlvssrgwhtmtqwmttptnmgzpwzzrbwtsrjhmjpblztgftppcwwrjppvwlvdhwwdlfcdvhwpvhqpwrqsczvlmtghrrvqbphljcmcrfmwltlmnzchzrlbgspdjwfbhrmnfhvjwlgtghcpbfgdvrmwbqbprfvwpzqzrgqbhjtztwhjcjtncmbgjphsgdfvjwjljlwzhsdldqtdvgtzpwtmrbnpvqrmfdwngzqtsdjjztslrwdnfwgbnsjblcjzvmgbqllmdvvvdcvplgbzmhcrpbbjbfzhjgfpmjtrpmgvshzvqhcbcjzfcsvqggjcllcnjlrwqfpzldswzjgzvqwvszhrntnvlcpdcfqhqrtmhbdjqpfblrsbrhdfdwfgbhsnnjpgjvfpssfmhdbdncfrqbzpbttrhfzqnrttltqbvmhglbdpwmdbmgwcdsdsflmcnphwvbbhgqpqmwbdsmqwhcdftdlcfnstlfnzzsjhqzlsdmhpvlcqvhhlpdtzlqzlvbbwhhdsqhntbtpjjvnjlrncfmvnqmzwpldgrbfcfgdtlmrfzcbqfhvhpmsrrlnqwfggwrjsmnpnqhvvcfsfspfrmhwmbpqfhprzsswrczttlczhcqvgqqsnbhhfszqbswgtcjgddhngtcvlwqqmqzntrcwgwjhmhlgclpgtmqnpgwwhnjfdvvjgmqjlzsmwztpsdzrjlshswzljlmdzfqmqbtgtzlsfwqvwrdchjvrdnwdbprfvdvczstvnzfzzfmzbwjhtflbmhlgmfzrdfrqwfbltwqlqrghlprppvlqwggvczdcnmrmblhcfmpzdmqppgwhbbrjlzsvmsfzlrdljftcrdfdgmvrccwszpjmvdnbmfdnsgmgzsdpbrlsqvsmcwwqlwtwbgfvwtsmpqlnhjchnzrpncgtcqgwqrmqmrwsbmdvqcpggpzcjgtrhvnbpprlwfnjlvrgrdjjvncjmmflrpczqnlbqczggssqfcjcrghqlsztpdjpbbfcdjzzdjbljlsczdmrgshdscvhnltrwchjcmjzmjbhldnqzwwpswsnsldcbdcdpdgpjgwrnfbcpjtvzlhvgggldcfqcwwppvltsvsmwzwdgmhnfggtgtwldmrglcvmstgmbgbjhwwhgdhfrbjlwfhjfppdmffblbbzrplhlhqlsrnsthvjtglqntzcncmvhqnlsvvrscrhlncgncjswfgcvgjlwsndzmsbhbdqsggdgrsfqtzwnjpsdlbsqjrrjwwlbptwqpfqwvpsrtrmstddzdbjjlwvfqcpfczpsnmcgbfpbwcpljdhrgsqftbnplccjphwsdbprqfqqfcvtcdznnhrbdqpccphvdgtspmzzdbnslnrvtfrtbhcfzfgmhrttmdpwftvccjbllhqgtmpgwvbdjgtvtfbfnnsfgzqjmrpbcmqhpfbstznbvgtbhnwbbnjfsthdgdrpfdtvrtmgbwzqqpnlltbshjvnhsmqhqwzgbsfqqccfznjtnnzdrgcvwnlffdgvvqzvwhwfswdmqlrsglntzsnwjzgrhhwzzshwsfmlmrbnmrqlptmjgmtqctrmddzghsgtrbbcsmhtcnrzwmvrjmrnmhbjmflrclvlbzwbgmtnmwqgfmbbnnrdvhqcflglvzbmjzjtvnmrbgghnccfrphjshsgtrhfmmghhpwgclfvzfbdccsfrlfwtsjjnhlndpwcjdtlllhcsvwrwsbppqwhfcvnsnrvthrsbgmgjhpmjdndmdqdgzfvbqfmgfjrnrjchstrjprfwfnjqblhjdgsstvtpcsvmpbhggnwzncpjdhrcllcghhprhwhfgsqpfzphrdlcbrccglsb"));
    }


}
