# 龍絆迎擊畢業場次簡易計算機 (Java)

原始碼在src內。

### 使用方法

1. 將`out/artifacts/DLIOEstimate_jar`內的內容複製到自己知道的位置。
2. 調整資料夾內`configs.properties`的參數。計算所需迎擊章可以使用[試算表](http://github.com/RaenonX/dragalia-data-track)輔助。
    - `sys.detail`: 顯示每次模擬跑完一場迎擊以後的背包最佳狀態。
    - `sys.coinonly`: 只計算幣，無視章。計算過程所吃的記憶體空間會少一點。
    - 以下參數代號解釋:
        - `<SIZE>`: 有`gold`、`silver`、`bronze`分別代表金、銀、銅。
        - `<ITEM>`: 有`coin`、`emblem`分別代表幣、章。
        - `<ELEMENT>`: 有`fire`、`water`、`wind`、`light`、`dark`分別代表火、水、風、光、暗。
    - `<ITEM>.avg.<SIZE>`: 指定物品每場平均掉落的量。
    - `<ITEM>.current(.<ELEMENT>).<SIZE>`: 指定物品現在擁有的數量。
    - `<ITEM>.needed.<SIZE>`: 指定物品還需要的數量。
    
2. 在檔案所在位置開啟命令列(`cmd`)，並執行以下命令。(必須安裝[JRE](https://www.java.com/en/download/windows-64bit.jsp))
    > java -jar DLIOEstimate.jar
    - 執行的第一個參數可以是參數檔案路徑 
        - 預設: `configs.properties`

### 備註
- 因為現實沒什麼時間，做出這個只花兩天，並沒有精雕細琢，所以 **運算可能有誤** 。
- 原始碼在`src/`內。歡迎自由修改。會用`Git`又願意協助修改的，歡迎修改、完善以後開PR。
