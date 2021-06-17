<template>
  <v-app-bar app dark>
    <router-link to="/" tag="span" class="pointerClass">
      <h2>IBM@Certifications</h2>
    </router-link>
    <v-spacer />

    <router-link to="/profile" tag="span" class="pointerClass">
      <span>Profile</span>
      <v-icon>mdi-account</v-icon>
    </router-link>
    <v-spacer />

    <span class="pointerClass" @click="logout">
      <span>Logout</span>
      <v-icon>mdi-logout</v-icon>
    </span>
    <v-spacer />
  </v-app-bar>
</template>

<script>
export default {
  name: "Header",
  methods: {
    logout() {
      this.$store.commit("loggedInMutation", false);
      localStorage.removeItem("loggedIn");
      location.reload();
    }
  }
};
</script>

<style scoped>
.pointerClass {
  cursor: pointer;
}
</style>
