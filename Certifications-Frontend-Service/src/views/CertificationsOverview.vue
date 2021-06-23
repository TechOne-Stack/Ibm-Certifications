<template>
  <div class="home bg-gradient-to-t from-blue-200 to-indigo-900">
    <v-row>
      <v-col cols="12">
        <div v-if="certifications !== undefined && certifications.length > 0">
          <v-row>
            <v-col cols="12">
              <v-layout align-center justify-center>
                <h1
                  class="text-4xl sm:text-5xl md:text-7xl font-bold text-gray-200 mt-3"
                >
                  <h1 class="text-center">Available Certifications</h1>
                </h1>
              </v-layout>
            </v-col>
          </v-row>
          <v-row>
            <v-col>
              <v-layout d-flex flex-wrap justify-space-around>
                <router-link
                  v-for="certification in certifications"
                  :key="certification.id"
                  :to="'/certificationDetail/' + certification.id"
                  tag="div"
                >
                  <CertificationItem
                    style="cursor: pointer"
                    :certification="certification"
                  />
                </router-link>
              </v-layout>
            </v-col>
          </v-row>
        </div>
        <div v-else>
          <h3>No certifications found</h3>
        </div>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import Vue from "vue";
import CertificationItem from "@/components/CertificationItem.vue";
import { mapGetters, mapMutations } from "vuex";
import axios from "axios";

export default Vue.extend({
  name: "Home",
  components: {
    CertificationItem
  },
  computed: {
    ...mapGetters(["certifications"])
  },
  async mounted() {
    if (this.loggedIn == false) {
      this.$router.push("/login");
    }
    const { data } = await axios.get("http://localhost:8080/certifications");
    console.log(data);
    this.certificationsMutation(data._embedded.certifications);
  },
  methods: {
    ...mapMutations(["certificationsMutation"])
  }
});
</script>
