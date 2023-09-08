# sysid-dbid-repository
システム識別子＋データソース識別子＋repository

システムのデータソースごとにrepository層を提供するモジュールのサンプル

サンプルはDBだがSolrや外部サービスなどの広義的なデータソースごとにわけて通信方式などをカプセル化する

## Requirements
- JDK 17

## Features
- Spring Boot 3
- Doma2
- Spock+Groovy
- Flyway
- SonarCloud
- GitHub Actions workflow

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

#### ローカルManveリポジトリにPublish
IDEまたはコマンドでpublishToMavenLocal
```sh
./gradlew publishToMavenLocal
```

#### Eclipseの設定
- メニューバーのEclipse > 設定... > Gradle で 自動的にプロジェクトを同期 のチェックを外す（Gradleの機能を使うためEclipsePluginはOFFにする）
- GradleタスクViewerで、ide > eclipse のタスクを実行する（クラスパス等が設定される）
