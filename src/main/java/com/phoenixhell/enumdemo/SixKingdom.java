package com.phoenixhell.enumdemo;

        import lombok.Getter;

@Getter
public enum SixKingdom {
    ONE(1,"齐"),TWO(2,"楚"),THREE(3,"赵"),FOUR(4,"韩"),FIVE(5,"魏"),SIX(6,"燕");


    private final Integer code;
    private final String kingdom;

    SixKingdom(Integer code, String kingdom) {
        this.code = code;
        this.kingdom = kingdom;
    }


    public static String get(Integer index){
        SixKingdom[] values = SixKingdom.values();
        for (SixKingdom kingdom : values) {
            if(kingdom.getCode()==index){
                return kingdom.getKingdom();
            }
        }
        return "t";
    }
}



