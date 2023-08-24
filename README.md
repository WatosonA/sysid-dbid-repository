# sysid-dbid-repository
システム識別子＋データソース識別子＋repository

システムのデータソースごとにrepository層を提供するモジュールのサンプル

サンプルはDBだがSolrや外部サービスなどの広義的なデータソースごとにわけて通信方式などをカプセル化する

## Requirements
- JDK 17

## Quick Start

### ローカル環境での起動方法

#### DB起動
```sh
cd .local/docker
docker-compose up -d
```

##### DBオブジェクトを作成
IDEまたはコマンドでflywayMigrateを実行する
```sh
./gradlew flywayMigrate_maindb
./gradlew flywayMigrate_testdb
```

#### アプリケーションを起動
IDEまたはコマンドでtest
```sh
./gradlew test
```
