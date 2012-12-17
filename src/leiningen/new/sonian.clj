(ns leiningen.new.sonian
  (:use [leiningen.new.templates :only [renderer year project-name
                                        ->files sanitize-ns name-to-path]]))

(defn sonian
  "THE ONE AND TRUE WAY."
  [name]
  (let [render (renderer "sonian")
        data {:raw-name name
              :name (project-name name)
              :namespace (sanitize-ns name)
              :nested-dirs (name-to-path name)
              :year (year)}]
    (println "Generating a project called" name "based on Sonian's template.")
    (->files data
             ["project.clj" (render "project.clj" data)]
             ["README.md" (render "README.md" data)]
             [".gitignore" (render "gitignore" data)]
             ["src/{{nested-dirs}}/core.clj" (render "core.clj" data)]
             ["test/{{nested-dirs}}/test/core.clj" (render "test.clj" data)])))
