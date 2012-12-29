require 'rspec'

describe 'unmarshalling a hash from MRI 1.9.1 that includes ASCII strings' do
  # Generate input file via:
  # $ ruby -v # => 1.9.1-p431
  # $ irb -E US-ASCII:US-ASCII
  # > x = {"a" => 1.0, "b" => 2.0}
  # > File.open('marshal-data.dump', 'w') { |f| Marshal.dump(x, f) }

  it "does not throw an encoding error" do
    dump_path = __FILE__.chomp(File.extname(__FILE__)) + ".dump"

    hash = File.open(dump_path) do |f|
      Marshal.load(f)
    end

    hash.should include("a" => 1.0)
    hash.should include("b" => 2.0)
  end
end
