<template>
  <v-card max-width="500" class="mx-auto">
    <v-toolbar color="indigo" dark>
      <v-app-bar-nav-icon></v-app-bar-nav-icon>

      <v-toolbar-title>Inbox</v-toolbar-title>

      <v-spacer></v-spacer>

      <v-btn icon>
        <v-icon>mdi-magnify</v-icon>
      </v-btn>

      <v-btn icon>
        <v-icon>mdi-dots-vertical</v-icon>
      </v-btn>
    </v-toolbar>
    <v-list>
      <!-- <v-list-item v-for="user in users" :key="user.id">
        <v-list-item-icon>
          <v-icon v-if="item.icon" color="pink">
            mdi-star
          </v-icon>
        </v-list-item-icon>

        <v-list-item-content>
          <v-list-item-title v-text="user.firstname"></v-list-item-title>
        </v-list-item-content>

        <v-list-item-avatar>
          <v-img :src="item.avatar"></v-img>
        </v-list-item-avatar>
      </v-list-item> -->
      <p v-for="user in users" :key="user.id" tag="div">{{ user.email }} {{ user.name}} {{ user.surname}}</p>
    </v-list>
  </v-card>
</template>

<script>
import axios from "axios";

export default {
  name: "All-users",
  data() {
    return {
      users: []
    };
  },
  async mounted() {
    const token = JSON.parse(localStorage.getItem("token"));
    const { data } = await axios.get("http://localhost:8080/users", {
      headers: {
        Authorization: 'Bearer ' + token 
      }
    });
    // for(let i = 0; i < data._embedded.users.lenght; i++){

    // }
    this.users = data._embedded.users;
    console.log(this.users);
  }
};
</script>
