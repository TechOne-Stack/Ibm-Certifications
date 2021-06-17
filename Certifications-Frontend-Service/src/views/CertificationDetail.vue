<template>
  <div>
    <h1>{{ currentCertification.name }}</h1>
    <p>{{ currentCertification.price }}</p>
    <span>{{ currentCertification.url }}</span>
  </div>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  name: "CertificationDetail",
  data() {
    return {
      currentCertification: {}
    };
  },
  created() {
    this.currentCertification = this.certifications.find(
      ({ id }) => id == this.$route.params.id
    );
  },
  computed: {
    ...mapGetters(["certifications"])
  }
};
</script>

<style></style>
