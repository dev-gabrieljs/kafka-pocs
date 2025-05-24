# Stage 1: Build do jar com Maven
# Usamos a imagem do Maven para construir a aplicação Java com OpenJDK 17
FROM maven:3.8.3-openjdk-17 AS build

# Define o diretório de trabalho no container como /app
WORKDIR /app

# Copia o arquivo pom.xml para o diretório de trabalho do container
# O pom.xml contém a configuração do Maven, como dependências, plugins, etc.
COPY pom.xml .

# Copia a pasta src (código-fonte) para o diretório de trabalho do container
COPY src ./src

# Executa o comando Maven para limpar qualquer build anterior e gerar o arquivo JAR
# O parâmetro -DskipTests faz com que os testes sejam ignorados durante o build
RUN mvn clean package -DskipTests

# Stage 2: Runtime com OpenJDK
# Usamos uma imagem do OpenJDK 17 para executar a aplicação gerada no estágio anterior
FROM openjdk:17

# Define o diretório de trabalho no container como /app
WORKDIR /app

# Copia o arquivo JAR gerado no primeiro estágio (do diretório /app/target)
# O arquivo JAR é copiado para o diretório de trabalho do container como 'app.jar'
COPY --from=build /app/target/kafka-poc-v1-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta 8080 no container, que é a porta padrão do Spring Boot
EXPOSE 8080

# Define o comando de entrada que será executado quando o container for iniciado
# O comando executa a aplicação Java usando o arquivo JAR copiado (app.jar)
ENTRYPOINT ["java", "-jar", "app.jar"]
