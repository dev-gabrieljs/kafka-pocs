# Stage 1: build do jar com Maven
# Usamos uma imagem com Maven e OpenJDK 17 para compilar o código-fonte da aplicação Java
FROM maven:3.8.3-openjdk-17 AS build

# Define o diretório de trabalho no container como /app
WORKDIR /app

# Copia o arquivo pom.xml para o diretório de trabalho no container
# O arquivo pom.xml contém a configuração do Maven, como dependências e plugins
COPY pom.xml .

# Copia a pasta src para o diretório de trabalho no container
# A pasta src contém o código-fonte da aplicação Java
COPY src ./src

# Executa o comando Maven para limpar qualquer build anterior e gerar o arquivo JAR
# O parâmetro -DskipTests impede que os testes sejam executados durante o build
RUN mvn clean package -DskipTests

# Stage 2: runtime com OpenJDK
# Neste estágio, usamos uma imagem mais leve apenas com OpenJDK 17 para rodar a aplicação, sem o Maven
FROM openjdk:17

# Define o diretório de trabalho no container como /app
WORKDIR /app

# Copia o JAR gerado no estágio de build para o diretório de trabalho no container
# O JAR gerado no estágio 1 será copiado do caminho /app/target/ no container de build para /app.jar no container final
COPY --from=build /app/target/kafka-poc-v1-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta 8080 no container. A aplicação Spring Boot, por padrão, escuta nesta porta.
EXPOSE 8080

# Define o comando de entrada para iniciar a aplicação Java usando o arquivo JAR gerado
ENTRYPOINT ["java", "-jar", "app.jar"]
