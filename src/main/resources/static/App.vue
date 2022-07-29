<template>
  <v-app>
    <v-app-bar>
      <v-toolbar-title>TestFiveApplication</v-toolbar-title>
      <div>
        {{ user }}
      </div>
      <input type="text"
             v-model="search"
             class="input"
      >
      <v-btn @click="findOrCreateU">Find Or Create</v-btn>
    </v-app-bar>
    <message-form :user="user" @create="sendMessage"/>
    <message-list :messages="messages"/>
  </v-app>
</template>

<script>
import MessageForm from "./components/MessageForm.vue";
import MessageList from "./components/MessageList.vue";
import axios from "axios";

export default {
  components: {
    MessageForm, MessageList
  },
  data() {
    return {
      messages: [
        {id: 1, sendFrom: 'user1', date: new Date(),title: 'title 1', text: 'text 1'},
        {id: 2, sendFrom: 'user2', date: new Date(),title: 'title 2', text: 'text 2'},
        {id: 3, sendFrom: 'user3', date: new Date(),title: 'title 3', text: 'text 3'}
      ],
      search: '',
      user: '',
    }
  },
  methods: {
    // sendMessage(message) {
    //   this.messages.push(message);
    // },
    async sendMessage(message) {
      if (this.user===message.sendTo){
        this.messages.push(message);
      }
      let url = '/users/send'
      const newMessage = {
        sentFrom: this.user,
        title: message.title,
        text: message.text,
        sentTo: message.sendTo
      }
      await axios.post(url, newMessage)
    },

    async findOrCreateU() {
      try {
        this.user = this.search;
        let url = '/users/' + this.user;
        const response = await axios.get(url);
        if (response.data.messages){
          this.messages = response.data.messages;
        }

        console.log(response);
        if (response.data.length === 0) {
          url = '/users'
          let newUser = {
            username: this.user
          }
          await axios.post(url, newUser)
          this.messages = [];
        }

        this.search = ''
      } catch (e) {
        alert("Enter Username")
      }
    },

  },

}
</script>

<style>
body {
  padding-top: 100px;
}

.input {
  border: 1px red;
  background: snow;
  padding: 5px;
  margin: 5px;
}
</style>