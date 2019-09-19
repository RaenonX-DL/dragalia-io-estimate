# 失落的龍絆-帝國迎擊戰-簡易計算機 (Java)

原始碼在src內。

### 使用方法

1. 點選有上角的 `Clone or download`，點選 `Download ZIP` 下載。
2. 解壓縮
3. 調整資料夾內 `out/artifacts/DLIOEstimate_jar/configs.properties` 的參數。<br/>
    計算所需迎擊章可以使用[試算表](http://github.com/RaenonX/dragalia-data-track)輔助。
4. configs.properties 參數說明：
    - sys.detail：顯示每次模擬跑完一場迎擊以後的背包最佳狀態。
    - sys.coinonly：只計算盧比，無視徽章。計算過程所吃的記憶體空間會少一點。
    - coin.avg.(bronze | silver | gold)：每次打完一戰平均得到盧比。
    - coin.current.(bronze | silver | gold)：你現在擁有的盧比。
    - coin.needed.(bronze | silver | gold)：你欠缺的盧比數量。
    - emblem.avg.(silver | gold)：每次打完一戰平均得到徽章。
    - emblem.current.(*ELEMENT*).(*TYPE*)：你現在擁有的徽章。
    - emblem.needed.(*ELEMENT*).(*TYPE*)：你欠缺的徽章數量。
        * *ELEMENT*：帝國迎擊的屬性，有火（fire）、水（water）、風（wind）、光（light）、闇（dark）。
        * *TYPE*：徽章的種類，有銀（silver）、金（gold）
    
5. Windows：在檔案所在位置開啟命令列(`cmd`)，並執行以下命令。<br/>
```shell
> cd out/artifacts/DLIOEstimate_jar/
> java -jar DLIOEstimate.jar
```
* 必須安裝[JRE](https://www.java.com/en/download/windows-64bit.jsp)
* 執行的第一個參數可以是參數檔案路徑（預設為: `configs.properties`）

6. macOS：開啟終端機，並執行以下命令。<br/>
```shell
$ cd ./out/artifacts/DLIOEstimate_jar/
$ java -jar DLIOEstimate.jar
```

* 必須安裝 JRE：
	- 從網站下載：https://www.java.com/zh_TW/download/help/mac_install.xml
	- 透過 [Homebrew](https://brew.sh/index_zh-tw) 安裝：`$ brew install java`
	
7. Liumx：開啟終端機，並執行以下命令。<br/>
```shell
$ cd ./out/artifacts/DLIOEstimate_jar/
$ java -jar DLIOEstimate.jar
```

* 必須安裝 JRE：
	- 從網站下載：https://www.java.com/zh_TW/download/help/linux_x64_install.xml
	- 透過 Apt-get 安裝：`$ apt-get install java`
	- 透過 yum 安裝：`$ yum install java`

### 備註
- 因為現實沒什麼時間，做出這個只花兩天，並沒有精雕細琢，所以 **運算可能有誤** 。
- 原始碼在`src/`內。歡迎自由修改。會用`Git`又願意協助修改的，歡迎修改、完善以後請 Pull request 給我，感謝您的付出。