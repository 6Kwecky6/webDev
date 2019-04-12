package Oving7;

import org.jvnet.fastinfoset.EncodingAlgorithmException;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

public class Crypt {
    public static void main(String[] args)throws UnsupportedEncodingException{
        //https://lapo.it/asn1js/#MIIBCgKCAQEA639u2haGdEoEQ5wf7lfTHEvDW2FuLBNmZgailV3N9L2JCI9NKtk1QOlEW2t6jweRfzjNf7Qs9XZkk6v6hveW2AZAYuhbNxQFT1FOk-Ez2RFVLLNZfIc-sXD0VURkORY7m-CFHfT-pf6hlLrvZONEWdJ1ZmxDtMOH6hTESCOooxdJ8m2-WsA5GuzOvaagZD_P4Gf9uoVjk_-G4jsB3YyaGAu-hs_Xx_ti9xPwFtCiUloJlUxhsDz9my67QMmPype4vv1w2Hhaj3UabCQi5qj4JgSctNayRy73Wk0iXtos1s2S38CUsUuSL7oZWDeIi2pZS0NT7e8cZllAHgSuX8MW-wIDAQAB
        String message="important message!";
        String encMessageTru= "9c3e8d77333fcee3885747250fd48c8a6a5a8e62c24f8ef5f578c752469880409f69fa94a70dae0f71acc7a3988cc81e66881cbc75d5096dedfeeb3d17fb88fd27abe5d32f3b705a11045a91b5b5986f34948009e9b35e8026f986ae871e986392ae37e0458223d62b05fbb50935f63fa920590454d7851d35bf7b3d4cf0752c4683666bcb0398843d141113f32442f8d38f7910a43102da331a6e56fd2a3b3dbe49abf15b4e93c5a81341ed9f87e6bd972536e185e2cde096105db51de519f980901585b2c312b8a097853434bf144a3f14182f2d1b971169280b15061b781a21b8954c626aa4d9417275c1b1812eb0b9770b8320db2f1093f6e775105d39d5";
        String Publickey="MIIBCgKCAQEA639u2haGdEoEQ5wf7lfTHEvDW2FuLBNmZgailV3N9L2JCI9NKtk1\n" +
                "QOlEW2t6jweRfzjNf7Qs9XZkk6v6hveW2AZAYuhbNxQFT1FOk+Ez2RFVLLNZfIc+\n" +
                "sXD0VURkORY7m+CFHfT+pf6hlLrvZONEWdJ1ZmxDtMOH6hTESCOooxdJ8m2+WsA5\n" +
                "GuzOvaagZD/P4Gf9uoVjk/+G4jsB3YyaGAu+hs/Xx/ti9xPwFtCiUloJlUxhsDz9\n" +
                "my67QMmPype4vv1w2Hhaj3UabCQi5qj4JgSctNayRy73Wk0iXtos1s2S38CUsUuS\n" +
                "L7oZWDeIi2pZS0NT7e8cZllAHgSuX8MW+wIDAQAB";
        byte[] encBin=message.getBytes("UTF8");
        BigInteger encInt = new BigInteger(encBin);
        BigInteger decInt = encInt.modPow(new BigInteger("65537"),new BigInteger("2972no8841228910348252537332641826641136702384626170397733390251177312772264785879714563344673895995755550237542563225435187992954252083812676212269968533340548610285115402686355934643048325638020181921307890461022698262856109811836010373109291470853546186806254275020101095938435762004872112520226361136500962820644200236973409096989361980573277364076849111213194095888632518631906485693109074293731538474036668594746696444189341230757416130968020215306475008340014809643803270970419370047534022927092968192763891494293744162967407297593788820890757256858497866760240288365630076684330564854710880405219572840671680251"));
        String string = decInt.toString();
        while (string.length() % 3 != 0)
        {
            string = '0' + string;
        }
        String result = "";
        for (int i = 0; i < string.length(); i += 3)
        {
            result += (char)(Integer.parseInt(string.substring(i, i + 3)));
        }
        System.out.println(result);
    }
}
