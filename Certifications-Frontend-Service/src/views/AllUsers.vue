<template>
  <v-simple-table>
    <flash-message class="myCustomClass"></flash-message>
    <template v-slot:default>
      <thead>
        <tr>
          <th class="text-center">
            Name
          </th>
          <th class="text-center">
            Surname
          </th>
          <th class="text-center">
            Email
          </th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="user in users" :key="user.id">
          <td>{{ user.name }}</td>
          <td>{{ user.surname }}</td>
          <td>{{ user.email }}</td>
          <td><button @click="removeUser(user.id)">Remove User</button></td>
        </tr>
      </tbody>
    </template>
  </v-simple-table>
</template>

<script>
import Vue from "vue";
import axios from "axios";
import VueFlashMessage from "vue-flash-message";

Vue.use(VueFlashMessage);

export default {
  name: "All-users",
  created() {
    this.$store.dispatch("loadUsers");
  },
  methods: {
    async removeUser(id) {
      const token = JSON.parse(localStorage.getItem("token"));
      try {
        await axios.delete("http://localhost:8080/api/test/delete-user/" + id, {
          headers: {
            Authorization: "Bearer " + token
          }
        });
      } catch (err) {
        if (err.response) {
          alert("RESPONSE ERROR - client received an error response");
        } else if (err.request){
          alert("REQUEST ERROR - client never received a response");
        } else {
          alert("something wrong");
        }
      }
      this.$store.dispatch("loadUsers");
    }
  },
  computed: {
    users: {
      get() {
        return this.$store.getters.users;
      },
      set(value) {
        this.$store.commit("usersMutation", value);
      }
    }
  }
};
</script>
