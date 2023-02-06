# CGSCRIPT

## 如何測試

請打包此專案成 jar，並且導入伺服器 /plugins 重新啟動即可運行。 可能會有錯誤我沒有除錯。

> `/cgscript test` 測試指令

然後自行看後台的除錯資訊，大多顯示command、scriptvarible、player、varname(原本用於設計傳遞前指令的命令，但覺得太不人性所以棄用了。)，有自動分行的顯示。

## 語法介紹

- var
    - get `%name%` 系統 placeholder(系統內建通識符替換器) 將會自動取代通識符的文字格式 PlaceHolderAPI 也會覆蓋數值
    - set `var.set(name, value)` *注意記得必須與這個格式相同", "
        - value 支持型態轉換 `(i) int` `(d) Double` `(f) Float` `(b) Boolean` 預設為 String
- player
    - check
        - inOnline 檢查是玩家否上線
        - isOnline(玩家名稱) 指定檢查玩家是否上線
    - sendMessage(字串) 對玩家顯示字串
- if
    - EQUAL; 使用 String 比對兩者是否相同
    - MORE; 使用 Int 比對是否大於
    - MOREEQUAL; 使用 Int 比對是否大於等於
    - LESS; 使用 Int 比對是否小於
    - LESSEQUAL; 使用 Int 比對是否小於等於
    - BOOLEAN; 使用 Boolean 比對是否 true