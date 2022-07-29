<template>
  <input type="text"
         class="input"
         v-model="message.sendTo"
         placeholder="send to"
  >
  <input type="text"
         class="input"
         v-model="message.title"
         placeholder="title"
  >
  <textarea class="input"
            v-model="message.text"
            placeholder="message"
  ></textarea>
  <v-btn @click="sendMessage">Send</v-btn>
</template>

<script>
export default {
  props:{
    user: {
      type: String,
      required: true
    }
  },
  data() {
    return {
      message: {
        sentFrom: '',
        sendTo: '',
        date: '',
        title: '',
        text: ''
      }
    }
  },
  emits: ['create'],
  methods: {
    sendMessage() {
      this.message.id = Date.now();
      this.message.sentFrom = this.user;
      this.message.date = new Date();
      this.$emit('create', this.message);
      this.message = {
        sendFrom: '',
        sentTo: '',
        title: '',
        text: ''
      };
    }
  }
}
</script>

<style scoped>
.input {
  border: 1px red;
  background: snow;
  padding: 5px;
  margin: 5px;
}
</style>