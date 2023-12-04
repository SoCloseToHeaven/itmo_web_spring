FROM node:18.17.0

ARG APP_DIR=app_node
RUN mkdir -p ${APP_DIR}
WORKDIR ${APP_DIR}

COPY package*.json ./
RUN npm install
RUN npm install -g serve

COPY . .
EXPOSE 3000
ENTRYPOINT ["npm","start"]